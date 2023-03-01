package org.eclipse.gemoc.executionframework.event.manager;
/*
 * implemented by Faezeh
 */
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gemoc.dsl.Dsl;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.osgi.framework.Bundle;

public class K3RuleExecutor implements IMetalanguageRuleExecutor{
	
	public K3RuleExecutor(String languageName) {
		loadLanguage(languageName);
	}

	@Override
	public Object handleCallRequest(SimpleCallRequest callRequest) {
		final Method rule = findMethod(callRequest);
		performCall(rule, callRequest.getArguments());
		return null;
	}
	private Object performCall(Method toCall, List<Object> args) {
		Object result = null;
		try {
			result = toCall.invoke(null, args.toArray());
		} catch (Throwable e) {
			if (e instanceof IllegalAccessException) {
				System.out.println("IllegalAccessException -> '" + toCall.getName() +
						"' method object is enforcing Java language access control and the underlying method is inaccessible");
			}
			else if (e instanceof IllegalArgumentException) {
				System.out.println("IllegalArgumentException -> '" + toCall.getName() +
						"' method is an instance method and the specified object argument is not an instance of the class or interface declaring the underlying method "
						+ "(or of a subclass or implementor thereof); "
						+ "if the number of actual and formal parameters differ; "
						+ "if an unwrapping conversion for primitive arguments fails; "
						+ "or if, after possible unwrapping, a parameter value cannot be converted to the corresponding formal parameter type by a method invocation conversion.");
			}
			else if (e instanceof InvocationTargetException) {
				//e.printStackTrace();
				System.out.println("InvocationTargetException -> thrown because of calling '" + toCall.getName() + "' method");
			}
			else if (e instanceof NullPointerException) {
				System.out.println("NullPointerException -> the specified object is null and the '" + toCall.getName() + "' method is an instance method");
			}
			else if (e instanceof ExceptionInInitializerError) {
				System.out.println("ExceptionInInitializerError -> the initialization provoked by '" + toCall.getName() + "' method fails.");
			}
		}
		return result;
	}
	private Method findMethod(SimpleCallRequest callRequest) {
		final Class<?>[] parameters = (Class[]) callRequest.getArguments().stream().map(o -> o.getClass())
				.collect(Collectors.toList()).toArray(new Class[0]);
		final String methodName = callRequest.getBehavioralUnit();
		final Method method = operationalSemantics.stream().filter(c -> methodName.startsWith(c.getName()))
				.map(c -> findMethodInClass(c, methodName.substring(c.getName().length() + 1), parameters))
				.filter(m -> m != null).findFirst().orElse(null);
		return method;
	}
	private final List<EPackage> packages = new ArrayList<>();

	private final List<Class<?>> operationalSemantics = new ArrayList<>();

	private void loadLanguage(String languageName) {
		final ResourceSet resSet = new ResourceSetImpl();
		IConfigurationElement language = Arrays
				.asList(Platform.getExtensionRegistry()
						.getConfigurationElementsFor("org.eclipse.gemoc.gemoc_language_workbench.xdsml"))
				.stream().filter(l -> l.getAttribute("xdsmlFilePath").endsWith(".dsl")
						&& l.getAttribute("name").equals(languageName))
				.findFirst().orElse(null);
		
		if (language != null) {
			String xdsmlFilePath = language.getAttribute("xdsmlFilePath");
			if (!xdsmlFilePath.startsWith("platform:/plugin")) {
				xdsmlFilePath = "platform:/plugin" + xdsmlFilePath;
			}
			final Resource res = resSet.getResource(URI.createURI(xdsmlFilePath), true);
			final Dsl dsl = (Dsl) res.getContents().get(0);
			final Bundle bundle = Platform.getBundle(language.getContributor().getName());

			if (dsl != null) {
				final List<EPackage> packages = dsl.getEntries().stream().filter(e -> e.getKey().equals("ecore"))
						.findFirst()
						.map(as -> Arrays.asList(as.getValue().split(", ")).stream()
								.map(s -> URI.createURI(s.replace("platform:/resource", "platform:/plugin"), true))
								.map(uri -> ((EPackage) resSet.getResource(uri, true).getContents().get(0)).getNsURI()))
						.map(uris -> uris.map(uri -> Arrays
								.asList(Platform.getExtensionRegistry()
										.getConfigurationElementsFor("org.eclipse.emf.ecore.generated_package"))
								.stream().filter(c -> c.getAttribute("uri").equals(uri)).map(c -> loadPackage(c))
								.filter(p -> p != null).findFirst().orElse(null)))
						.map(s -> s.collect(Collectors.toList())).orElse(Collections.emptyList());

				final List<Class<?>> classes = dsl.getEntries().stream().filter(e -> e.getKey().equals("k3"))
						.findFirst()
						.map(os -> Arrays.asList(os.getValue().split(",")).stream().map(cn -> loadClass(cn, bundle))
								.filter(c -> c != null).collect(Collectors.toList()))
						.orElse(Collections.emptyList()).stream().map(c -> (Class<?>) c).collect(Collectors.toList());

				this.packages.addAll(packages);
				this.operationalSemantics.addAll(classes);
			}
		}
	}

	private EPackage loadPackage(IConfigurationElement configurationElement) {
		EPackage result = null;
		try {
			List<Field> fields = Arrays.asList(Platform.getBundle(configurationElement.getContributor().getName())
					.loadClass(configurationElement.getAttribute("class")).getFields());
			result = fields.stream().filter(f -> f.getName().equals("eINSTANCE")).findFirst().map(f -> {
				try {
					return (EPackage) f.get(null);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				return null;
			}).orElse(null);
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
	private Class<?> loadClass(String className, Bundle bundle) {
		Class<?> result = null;
		try {
			result = bundle.loadClass(className.replaceAll("\\s", "").trim());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	private Method findMethodInClass(Class<?> clazz, String name, Class<?>[] parameters) {
		return Arrays.stream(clazz.getDeclaredMethods()).map(m -> {
			Method result = null;
			if (m.getName().equals(name) && m.getParameterCount() == parameters.length) {
				final Class<?>[] t = m.getParameterTypes();
				result = m;
				for (int i = 0; i < t.length; i++) {
					Class<?> type = t[i];
					if (!type.isAssignableFrom(parameters[i])) {
						result = null;
						break;
					}
				}
			}
			return result;
		}).filter(m -> m != null).findFirst().orElse(null);
	}

	@Override
	public void setExecutionEngine(IExecutionEngine<?> executionEngine) {
		// TODO Auto-generated method stub
		
	}

}
