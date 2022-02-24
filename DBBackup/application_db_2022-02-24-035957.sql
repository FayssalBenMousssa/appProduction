-- MySQL dump 10.13  Distrib 8.0.27, for Linux (x86_64)
--
-- Host: localhost    Database: application_db
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `app__exception`
--

DROP TABLE IF EXISTS `app__exception`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app__exception` (
  `id` bigint NOT NULL,
  `error` varchar(255) DEFAULT NULL,
  `errors` longtext,
  `exception` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `trace` longtext,
  `user_id` bigint DEFAULT NULL,
  `message` longtext,
  PRIMARY KEY (`id`),
  KEY `FKk646pgxp22jnv1of70c7b3g90` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app__exception`
--

LOCK TABLES `app__exception` WRITE;
/*!40000 ALTER TABLE `app__exception` DISABLE KEYS */;
INSERT INTO `app__exception` VALUES (7267,'Internal Server Error',NULL,'io.jsonwebtoken.security.SignatureException','/api/product/index','500','2022-02-23 08:48:27','io.jsonwebtoken.security.SignatureException: JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.\r\n	at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:420)\r\n	at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:550)\r\n	at io.jsonwebtoken.impl.DefaultJwtParser.parseClaimsJws(DefaultJwtParser.java:610)\r\n	at dev.fenix.application.api.util.JwtUtil.extractAllClaims(JwtUtil.java:36)\r\n	at dev.fenix.application.api.util.JwtUtil.extractClaim(JwtUtil.java:30)\r\n	at dev.fenix.application.api.util.JwtUtil.extractUsername(JwtUtil.java:22)\r\n	at dev.fenix.application.api.filters.JwtRequestFilter.doFilterInternal(JwtRequestFilter.java:37)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:103)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:89)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:91)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doHeadersAfter(HeaderWriterFilter.java:90)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:75)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:110)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:80)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:55)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:211)\r\n	at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:183)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:358)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:271)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:202)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:542)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:143)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:374)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)\r\n	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:888)\r\n	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1597)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1130)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.base/java.lang.Thread.run(Thread.java:832)\r\n',NULL,'JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.'),(7269,'Internal Server Error',NULL,'io.jsonwebtoken.ExpiredJwtException','/api/product/save/','500','2022-02-23 11:06:52','io.jsonwebtoken.ExpiredJwtException: JWT expired at 2022-01-30T13:06:34Z. Current time: 2022-02-23T11:06:51Z, a difference of 2066417810 milliseconds.  Allowed clock skew: 0 milliseconds.\r\n	at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:448)\r\n	at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:550)\r\n	at io.jsonwebtoken.impl.DefaultJwtParser.parseClaimsJws(DefaultJwtParser.java:610)\r\n	at dev.fenix.application.api.util.JwtUtil.extractAllClaims(JwtUtil.java:36)\r\n	at dev.fenix.application.api.util.JwtUtil.extractClaim(JwtUtil.java:30)\r\n	at dev.fenix.application.api.util.JwtUtil.extractUsername(JwtUtil.java:22)\r\n	at dev.fenix.application.api.filters.JwtRequestFilter.doFilterInternal(JwtRequestFilter.java:37)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:103)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:89)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:91)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doHeadersAfter(HeaderWriterFilter.java:90)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:75)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:110)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:80)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:55)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:211)\r\n	at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:183)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:358)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:271)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:202)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:542)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:143)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:374)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)\r\n	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:888)\r\n	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1597)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1130)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.base/java.lang.Thread.run(Thread.java:832)\r\n',NULL,'JWT expired at 2022-01-30T13:06:34Z. Current time: 2022-02-23T11:06:51Z, a difference of 2066417810 milliseconds.  Allowed clock skew: 0 milliseconds.'),(7286,'Internal Server Error',NULL,'javassist.NotFoundException','/api/product/get/7282','500','2022-02-23 13:21:15','javassist.NotFoundException: Product  not found\r\n	at dev.fenix.application.api.production.product.ProductResource.lambda$get$0(ProductResource.java:89)\r\n	at java.base/java.util.Optional.orElseThrow(Optional.java:401)\r\n	at dev.fenix.application.api.production.product.ProductResource.get(ProductResource.java:89)\r\n	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:64)\r\n	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.base/java.lang.reflect.Method.invoke(Method.java:564)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:197)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:141)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:106)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:894)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1061)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:961)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)\r\n	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:898)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:626)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:733)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:113)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:327)\r\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.invoke(FilterSecurityInterceptor.java:115)\r\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.doFilter(FilterSecurityInterceptor.java:81)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:119)\r\n	at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:113)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:126)\r\n	at org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:81)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.authentication.AnonymousAuthenticationFilter.doFilter(AnonymousAuthenticationFilter.java:105)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(SecurityContextHolderAwareRequestFilter.java:149)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.savedrequest.RequestCacheAwareFilter.doFilter(RequestCacheAwareFilter.java:63)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.authentication.www.BasicAuthenticationFilter.doFilterInternal(BasicAuthenticationFilter.java:149)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at dev.fenix.application.api.filters.JwtRequestFilter.doFilterInternal(JwtRequestFilter.java:52)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:103)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:89)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:91)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doHeadersAfter(HeaderWriterFilter.java:90)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:75)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:110)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:80)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:55)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:211)\r\n	at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:183)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:358)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:271)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:202)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:542)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:143)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:374)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)\r\n	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:888)\r\n	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1597)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1130)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.base/java.lang.Thread.run(Thread.java:832)\r\n',NULL,'Product  not found');
/*!40000 ALTER TABLE `app__exception` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app__notification`
--

DROP TABLE IF EXISTS `app__notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app__notification` (
  `id` bigint NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `in_app` bit(1) DEFAULT NULL,
  `is_read` bit(1) NOT NULL,
  `is_trash` bit(1) NOT NULL,
  `on_email` bit(1) DEFAULT NULL,
  `notification_source` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa3a0u5yqonbnwwqihae8j6qha` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app__notification`
--

LOCK TABLES `app__notification` WRITE;
/*!40000 ALTER TABLE `app__notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `app__notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bz__job`
--

DROP TABLE IF EXISTS `bz__job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bz__job` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bz__job`
--

LOCK TABLES `bz__job` WRITE;
/*!40000 ALTER TABLE `bz__job` DISABLE KEYS */;
INSERT INTO `bz__job` VALUES (6,'Manager'),(7,'Vendeur'),(8,'Magasinier'),(180,'manager');
/*!40000 ALTER TABLE `bz__job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bz__staff`
--

DROP TABLE IF EXISTS `bz__staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bz__staff` (
  `id` bigint NOT NULL,
  `end_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `job_id` bigint NOT NULL,
  `personnel_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKi03754g0tl119aoonbugwfukw` (`job_id`),
  KEY `FKncxf4wlooo2tgmnohldn7n0yk` (`personnel_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bz__staff`
--

LOCK TABLES `bz__staff` WRITE;
/*!40000 ALTER TABLE `bz__staff` DISABLE KEYS */;
INSERT INTO `bz__staff` VALUES (1942,NULL,'2021-08-24 23:00:00',6,1695),(1943,NULL,'2021-08-24 23:00:00',7,1);
/*!40000 ALTER TABLE `bz__staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bz__team`
--

DROP TABLE IF EXISTS `bz__team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bz__team` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `leader_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3xw7lv7qnnb9r1wegby4iaj7i` (`leader_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bz__team`
--

LOCK TABLES `bz__team` WRITE;
/*!40000 ALTER TABLE `bz__team` DISABLE KEYS */;
INSERT INTO `bz__team` VALUES (1941,'Développement',1695);
/*!40000 ALTER TABLE `bz__team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bz__team_person`
--

DROP TABLE IF EXISTS `bz__team_person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bz__team_person` (
  `team_id` bigint NOT NULL,
  `person_id` bigint NOT NULL,
  PRIMARY KEY (`team_id`,`person_id`),
  KEY `FKptdw3yaynpvsiyg70krk9uqsl` (`person_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bz__team_person`
--

LOCK TABLES `bz__team_person` WRITE;
/*!40000 ALTER TABLE `bz__team_person` DISABLE KEYS */;
INSERT INTO `bz__team_person` VALUES (536,1),(559,1),(1027,1),(1941,1),(1941,1695),(1941,1889);
/*!40000 ALTER TABLE `bz__team_person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `core__address`
--

DROP TABLE IF EXISTS `core__address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `core__address` (
  `id` bigint NOT NULL,
  `active` bit(1) NOT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `line_one` varchar(255) DEFAULT NULL,
  `line_tow` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `zip_code` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core__address`
--

LOCK TABLES `core__address` WRITE;
/*!40000 ALTER TABLE `core__address` DISABLE KEYS */;
INSERT INTO `core__address` VALUES (6531,_binary '\0','CASABLANCA','Maroc','test adresse 2','modif adresse','Adresse siège',20060),(6487,_binary '','Casablanca ','Maroc','Complexe Sportif Bourgogne','Rue Aïn Oulmes','Adresse de livraison fenix',20250),(6488,_binary '','Casablanca','Maroc','Cosa Mia','Bd Aïn Taoujtate','Address de facturation ',20250),(6489,_binary '\0','fes','maroc','Depot 5','Ave Allal Ben Abdellah, ','Depot de fes ****',3000),(6516,_binary '','xxxxx','Maroc',NULL,NULL,'fayssal Benmoussa',56656),(6517,_binary '\0','xxxxx','Maroc',NULL,NULL,'fayssal Benmoussa',56656),(6518,_binary '\0','xxxxx','Maroc','Cosa Mia555','fgfg','fayssal Benmoussa ',5555555),(6519,_binary '','xxxxx','Maroc','Cosa Mia555','fgfg','fayssal Benmoussa',0),(6520,_binary '\0','xxxxx','Maroc','Cosa Mia555','xxxxxxxxxxx','fayssal Benmoussa',0),(6522,_binary '','FES','Maroc','AIN SEBAA',NULL,'fayssal Benmoussa',12211222221),(6523,_binary '\0','FES','Maroc','AIN SEBAA','HELLO ','fayssal Benmoussa',12211222221),(6524,_binary '','xxxxx','Maroc','Première ligne','Première ligne','fayssal Benmoussa',0),(6525,_binary '','xxxxx','Maroc','Cosa Mia555','fgfg','fayssal Benmoussa',888888),(6526,_binary '','xxxxx','Maroc','Cosa Mia555','fgfg','fayssal Benmoussa',888888),(6527,_binary '\0','xxxxx','Maroc','Cosa Mia555','fgfg','fayssal Benmoussa',888888),(6528,_binary '','CASABLANCA','Maroc','test adresse',NULL,'Adresse siège',20060),(6529,_binary '','CASABLANCA','Maroc','test adresse 2','modif adresse','Adresse siège',20060),(6530,_binary '\0','CASABLANCA','Maroc','test adresse 2','modif adresse','Adresse siège',20060),(6515,_binary '\0','xxxxx','Maroc','sqsqs','fdfdf','adress fes ',8585858),(6514,_binary '\0','casa negra','Maroc','Cosa Mia555',NULL,'Adress de pointage ',156),(6532,_binary '','PARIS','FRANCE','KLQSJMLFJSQM  LKMQJMLFJ',NULL,'ADRESSE SIEGE',0),(6533,_binary '','PARIS','FRANCE','KLQSJMLFJSQM  LKMQJMLFJ',NULL,'ADRESSE SIEGE',0),(7070,_binary '','xxxxx','Maroc',NULL,NULL,'fayssal Benmoussa',56565656),(7075,_binary '','xxxxx','Maroc',NULL,NULL,'fayssal Benmoussa',54545),(7076,_binary '','Casablanca','Maroc',NULL,NULL,'Adresse Usine',0),(7077,_binary '','Casablanca','Maroc',NULL,NULL,'Adresse Usine',0);
/*!40000 ALTER TABLE `core__address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `core__city`
--

DROP TABLE IF EXISTS `core__city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `core__city` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `id_country` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKekulve29aew12w3v9u2hls9dd` (`id_country`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core__city`
--

LOCK TABLES `core__city` WRITE;
/*!40000 ALTER TABLE `core__city` DISABLE KEYS */;
/*!40000 ALTER TABLE `core__city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `core__contact`
--

DROP TABLE IF EXISTS `core__contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `core__contact` (
  `id` bigint NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `job` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core__contact`
--

LOCK TABLES `core__contact` WRITE;
/*!40000 ALTER TABLE `core__contact` DISABLE KEYS */;
INSERT INTO `core__contact` VALUES (1,'email@gmail.com','coder2','fayssal','Import contact2','0644490000',_binary '\0'),(2,'email@gmail.com','designer','adil','problèmes de communication','0645214505',_binary '\0'),(3,'email@gmail.com','jobss','9666','Import contact566','0645450525',_binary '\0'),(6538,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6539,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6540,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6541,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6542,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6543,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6544,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6545,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6546,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6547,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6548,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6549,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6550,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6551,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6552,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6553,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6554,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6555,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6556,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6557,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6558,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6559,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6560,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6561,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6562,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6563,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6564,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6569,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6565,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6566,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6567,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6568,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6574,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6578,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6580,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6582,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6585,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6571,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6573,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6575,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6576,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6583,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6588,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6598,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6601,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6605,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6609,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6612,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6615,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6586,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6589,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6595,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6597,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6600,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6602,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6604,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6607,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6610,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6613,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6618,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6619,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6621,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6591,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6593,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6594,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6596,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6599,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6603,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6606,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6614,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6617,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6620,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6624,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6608,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6611,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6616,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6622,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6623,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6625,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6626,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6628,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6629,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6630,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6631,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6632,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6570,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6634,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6572,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6636,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6577,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6579,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6581,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6633,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6635,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6637,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6640,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6641,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6584,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6587,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6590,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6592,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6627,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6638,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6643,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6647,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6648,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6651,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6653,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6656,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6657,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6658,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6660,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6661,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6665,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6668,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6670,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6675,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6678,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6686,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6691,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6694,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6650,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6652,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6654,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6655,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6659,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6662,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6663,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6664,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6667,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6669,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6673,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6677,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6681,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6683,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6687,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6695,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6699,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6639,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6642,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6644,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6645,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6646,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6649,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6671,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6674,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6679,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6685,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6689,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6690,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6693,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6704,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6705,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6672,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6676,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6680,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6682,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6684,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6711,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6714,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6716,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6718,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6722,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6724,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6727,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6731,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6735,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6736,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6741,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6744,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6746,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6747,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6750,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6692,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6696,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6697,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6698,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6700,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6701,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6702,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6703,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6708,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6709,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6712,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6734,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6738,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6742,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6749,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6752,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6755,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6761,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6666,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6688,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6706,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6707,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6710,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6729,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6732,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6737,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6740,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6745,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6748,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6751,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6753,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6756,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6758,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6760,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6765,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6766,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6767,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6721,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6723,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6725,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6730,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6733,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6739,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6743,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6754,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6757,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6759,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6762,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6763,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6764,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6768,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6769,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6770,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6771,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6774,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6775,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6777,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6790,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6795,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6713,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6715,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6717,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6719,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6720,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6726,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6728,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6778,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6779,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6787,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6788,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6808,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6813,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6814,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6816,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6821,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6825,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6832,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6835,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6838,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6843,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6772,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6773,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6776,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6780,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6781,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6782,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6783,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6784,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6785,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6793,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6794,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6800,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6805,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6807,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6810,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6818,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6828,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6842,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6844,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6851,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6856,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6863,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6869,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6870,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6796,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6798,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6802,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6803,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6820,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6823,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6826,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6834,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6840,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6846,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6847,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6848,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6854,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6858,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6859,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6860,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6866,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6875,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6880,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6883,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6886,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6888,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6890,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6894,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6907,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6909,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6831,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6833,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6837,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6841,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6852,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6853,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6861,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6871,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6874,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6877,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6879,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6881,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6884,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6887,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6889,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6891,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6893,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6895,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6898,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6900,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6902,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6904,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6906,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6911,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6913,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6914,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6917,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6806,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6809,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6811,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6812,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6815,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6817,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6819,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6822,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6827,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6829,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6864,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6865,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6896,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6899,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6901,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6919,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6922,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6923,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6924,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6925,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6926,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6927,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6928,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6845,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6849,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6857,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6862,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6867,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6868,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6872,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6873,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6876,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6878,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6882,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6885,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6892,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6897,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6908,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6910,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6912,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6915,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6916,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6918,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6921,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6929,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6930,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6932,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6933,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6934,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6935,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6938,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6786,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6789,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6791,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6792,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6797,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6799,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6801,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6804,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6824,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+2126444954700',_binary '\0'),(6830,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6836,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6839,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6850,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6855,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6903,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6905,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6920,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6931,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6936,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6937,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6939,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6940,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6941,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6942,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6943,'admin@example.com','job44432323','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6944,'admin@example.com','5555','fayssal Benmoussa','je suis la','+212644495470',_binary ''),(6945,'admin@example.com','5555','fayssal Benmoussa','je suis la','+212644495470',_binary ''),(6946,'admin@example.com','5555','fayssal Benmoussa','je suis la','+212644495470',_binary ''),(6947,'admin@example.com','5555','fayssal Benmoussa','je suis la','+212644495470',_binary ''),(6948,'admin@example.com','5555','fayssal Benmoussa','je suis la','+212644495470',_binary ''),(6949,'admin@example.com','5555545545','fayssal Benmoussa','je suis la','+212644495470',_binary ''),(6950,'admin@example.com','5555','fayssal Benmoussa','je suis la','+212644495470',_binary ''),(6951,'admin@example.com','5555','fayssal Benmoussa','je suis la','+212644495470',_binary ''),(6952,'admin@example.com','5555545545','fayssal Benmoussa','je suis la','+212644495470',_binary ''),(6953,'admin@example.com','5555545545','fayssal Benmoussa','je suis la','+212644495470',_binary ''),(6954,'admin@example.com','5555','fayssal Benmoussa','je suis la','+212644495470',_binary '\0'),(6955,'admin@example.com','5555','fayssal Benmoussa','je suis la','+212644495470',_binary '\0'),(6956,'admin@example.com','5555545545','fayssal Benmoussa','je suis la','+212644495470',_binary '\0'),(6957,'admin@example.com','5555545545','fayssal Benmoussa jjjj','note de service','+212644495470',_binary '\0'),(6958,'admin@example.com','5555545545','fayssal Benmoussa','je suis la','+212644495470000',_binary '\0'),(6970,'admin@example.com','job444','fayssal Benmoussa','je suis la 2','+212644495470',_binary ''),(6971,'admin@example.com','j45b444ddd','fayssal Benmoussa','je suis la','+212644495470',_binary ''),(6972,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6973,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary ''),(6974,'Fayssal.note@gmail.com','jobss',' Benmoussa','je suis la  ','+212644495470',_binary ''),(6975,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6976,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6977,'admin@example.com','coder2','Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6978,'admin@example.com','job444','snmoussa','je suis la','+212644495470',_binary ''),(6979,'admin@example.com','job444','Fmoussa','Test','+212644495470',_binary '\0'),(6980,NULL,'job444','fayssal Benmoussa',NULL,'+212644495470',_binary '\0'),(6981,NULL,NULL,'fayssal Benmoussa',NULL,'+212644495470',_binary '\0'),(6982,NULL,NULL,'fayssal Benmoussa',NULL,'+212644495470',_binary '\0'),(6983,'admin@example.com','job444','fayssal Benmoussa','je suis la  ','+212644495470',_binary '\0'),(6984,NULL,NULL,'fayssal',NULL,'+212644495470',_binary '\0'),(6985,NULL,NULL,'fayssal Benmoussa',NULL,'',_binary '\0'),(7071,NULL,NULL,'fayssal Benmoussa',NULL,'+212644495470',_binary ''),(7073,NULL,NULL,'fayssal Benmoussa',NULL,'+212644495470',_binary '\0'),(7074,NULL,NULL,'fayssal Benmoussa',NULL,'+212644495470',_binary ''),(7078,'slaoui@test.net','Gérant','Slaoui Mehdi',NULL,'06 61 23 23 23',_binary '');
/*!40000 ALTER TABLE `core__contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `core__country`
--

DROP TABLE IF EXISTS `core__country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `core__country` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `capital` varchar(250) NOT NULL,
  `code` varchar(250) NOT NULL,
  `iso_code` varchar(250) NOT NULL,
  `name` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core__country`
--

LOCK TABLES `core__country` WRITE;
/*!40000 ALTER TABLE `core__country` DISABLE KEYS */;
/*!40000 ALTER TABLE `core__country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `core__metadata`
--

DROP TABLE IF EXISTS `core__metadata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `core__metadata` (
  `id` bigint NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `active` tinyint(1) DEFAULT '1',
  `required` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core__metadata`
--

LOCK TABLES `core__metadata` WRITE;
/*!40000 ALTER TABLE `core__metadata` DISABLE KEYS */;
INSERT INTO `core__metadata` VALUES (1,'2022-01-13 11:06:55','2022-01-13 11:06:55','Caractéristiques','text','characteristics',1,1),(2,'2022-01-13 11:06:55','2022-01-13 11:06:55','utilisations','text','uses',1,1);
/*!40000 ALTER TABLE `core__metadata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `core__tag`
--

DROP TABLE IF EXISTS `core__tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `core__tag` (
  `id` bigint NOT NULL,
  `name` varchar(255) NOT NULL,
  `parent_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkmw2fw4dhhw65ycsjvw0btktf` (`parent_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core__tag`
--

LOCK TABLES `core__tag` WRITE;
/*!40000 ALTER TABLE `core__tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `core__tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dev__a`
--

DROP TABLE IF EXISTS `dev__a`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dev__a` (
  `id` int NOT NULL AUTO_INCREMENT,
  `credit_card_number` longtext,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dev__a`
--

LOCK TABLES `dev__a` WRITE;
/*!40000 ALTER TABLE `dev__a` DISABLE KEYS */;
/*!40000 ALTER TABLE `dev__a` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dev__b`
--

DROP TABLE IF EXISTS `dev__b`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dev__b` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `a_id` int DEFAULT NULL,
  `cart_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbs5dll7ja9k0kpp5d09nrwt0n` (`a_id`),
  KEY `FKom6a7n0djalj23s3n1yaxyfci` (`cart_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dev__b`
--

LOCK TABLES `dev__b` WRITE;
/*!40000 ALTER TABLE `dev__b` DISABLE KEYS */;
/*!40000 ALTER TABLE `dev__b` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (7391),(1),(1),(1),(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pe__metadata`
--

DROP TABLE IF EXISTS `pe__metadata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pe__metadata` (
  `id` bigint NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pe__metadata`
--

LOCK TABLES `pe__metadata` WRITE;
/*!40000 ALTER TABLE `pe__metadata` DISABLE KEYS */;
/*!40000 ALTER TABLE `pe__metadata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pe__person`
--

DROP TABLE IF EXISTS `pe__person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pe__person` (
  `id` bigint NOT NULL,
  `birth_date` datetime DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3dsysntuqqp0biaexccbyndv2` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pe__person`
--

LOCK TABLES `pe__person` WRITE;
/*!40000 ALTER TABLE `pe__person` DISABLE KEYS */;
INSERT INTO `pe__person` VALUES (1,NULL,'2021-05-16 13:28:07','fayssal','benmoussa',NULL,2,NULL),(1889,NULL,'2021-08-24 11:31:12','issam','elyazri',NULL,1890,NULL),(1695,NULL,'2021-08-24 10:28:11','ZIZI','Mohamed',NULL,1696,NULL),(4074,'2021-02-10 23:00:00','2021-09-10 15:28:51','admin','admin','2021-02-10 23:00:00',4075,'MALE'),(5014,NULL,'2021-09-30 14:33:00','test','test',NULL,5015,NULL),(6117,NULL,'2021-12-08 09:20:31','Moahammed','ZIZI',NULL,6118,NULL);
/*!40000 ALTER TABLE `pe__person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prds__classification`
--

DROP TABLE IF EXISTS `prds__classification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prds__classification` (
  `id` bigint NOT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `level` int NOT NULL,
  `parent_id` bigint DEFAULT NULL,
  `active` tinyint(1) DEFAULT '1',
  `parent_name` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrib8u9lq45r6tsag26rwii0md` (`parent_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prds__classification`
--

LOCK TABLES `prds__classification` WRITE;
/*!40000 ALTER TABLE `prds__classification` DISABLE KEYS */;
INSERT INTO `prds__classification` VALUES (57,'SRN1','sauces rouges nature',2,22,1,'sauces chaudes',NULL,NULL),(56,'SRA','sauces rouges assaisonnées',2,22,1,'sauces chaudes',NULL,NULL),(55,'SJA','sauces jaunes assaisonnées',2,22,1,'sauces chaudes',NULL,NULL),(54,'SBN','sauces blanches nature',2,23,1,'sauces froides',NULL,NULL),(53,'SBA','sauces blanches assaisonnées',2,23,1,'sauces froides',NULL,NULL),(52,'OFM','œufs liquidesfrais melange',2,18,1,'œufs liquides frais blancs melange',NULL,NULL),(51,'OPJ','œufs liquides pasteurisés jaunes',2,21,1,'œufs liquides pasteurisés jaunes',NULL,NULL),(50,'OPE','œufs liquides pasteurisés entiers',2,20,1,'œufs liquides pasteurisés entiers',NULL,NULL),(49,'OPE','œufs liquides pasteurisés entiers',2,20,1,'œufs liquides pasteurisés entiers',NULL,NULL),(48,'OPB','œufs liquides pasteurisés blancs',2,19,1,'œufs liquides pasteurisés blancs',NULL,NULL),(47,'OFB','œufs liquides frais blanc',2,17,1,'œufs liquides frais blancs',NULL,NULL),(46,'DSN','nappage',2,11,1,'decoration \n de surface',NULL,NULL),(45,'DSN','nappage',2,12,1,'decoration  de surface',NULL,NULL),(44,'MJ','mozzarel jaune',2,16,1,'mozzarel',NULL,NULL),(43,'MJ','mozzarel jaune',2,16,1,'mozzarel',NULL,NULL),(42,'MB','mozzarel',2,16,1,'mozzarel',NULL,NULL),(41,'MS','mamie zakia salade',2,15,1,'mamie zakia',NULL,NULL),(40,'MZ','mamie zakia',2,15,1,'mamie zakia',NULL,NULL),(39,'MZ','mamie zakia',2,15,1,'mamie zakia',NULL,NULL),(38,'IP','impérial rouge',2,14,1,'impérial',NULL,NULL),(37,'IP','impérial rouge',2,14,1,'impérial',NULL,NULL),(36,'DSG','glaçage',2,11,1,'decoration \n de surface',NULL,NULL),(35,'DSG','glaçage',2,12,1,'decoration  de surface',NULL,NULL),(34,'DFE','emulsifiants',2,13,1,'fourrage',NULL,NULL),(33,'CDP','crémes de patisserie',2,10,1,'crémes froides',NULL,NULL),(32,'CDG','créme de glace',2,10,1,'crémes froides',NULL,NULL),(31,'CDC','créme de cuisson',2,9,1,'crémes chaudes',NULL,NULL),(30,'APA','aromes plantes aromatiques',2,8,1,'aromes poudres',NULL,NULL),(29,'APA','aromes plantes aromatiques',2,7,1,'aromes liquides',NULL,NULL),(28,'AGD','aromes goûts divers',2,8,1,'aromes poudres',NULL,NULL),(27,'AGD','aromes goûts divers',2,7,1,'aromes liquides',NULL,NULL),(26,'AFS','aromes fruits secs',2,7,1,'aromes liquides',NULL,NULL),(25,'AFF','aromes fruits frais',2,7,1,'aromes liquides',NULL,NULL),(24,'FTC','top chef',1,5,1,'préparation fromagère',NULL,NULL),(22,'FSC','sauces chaudes',1,6,1,'sauces',NULL,NULL),(23,'FSF','sauces froides',1,6,1,'sauces',NULL,NULL),(21,'FŒLPJ','œufs liquides pasteurisés jaunes',1,4,1,'œufs liquides pasteurisés',NULL,NULL),(20,'FŒLPE','œufs liquides pasteurisés entiers',1,4,1,'œufs liquides pasteurisés',NULL,NULL),(19,'FŒLPB','œufs liquides pasteurisés blancs',1,4,1,'œufs liquides pasteurisés',NULL,NULL),(18,'FŒLFBM','œufs liquides frais blancs melange',1,3,1,'œufs liquides frais',NULL,NULL),(1,'GC','crémes',0,NULL,1,'',NULL,NULL),(2,'GIP','intrants pâtissiers',0,NULL,1,'',NULL,NULL),(3,'GŒLF','œufs liquides frais',0,NULL,1,'',NULL,NULL),(4,'GŒLP','œufs liquides pasteurisés',0,NULL,1,'',NULL,NULL),(5,'GPF','préparation fromagère',0,NULL,1,'',NULL,NULL),(6,'GS','sauces',0,NULL,1,'',NULL,NULL),(7,'FAL','aromes liquides',1,2,1,'intrants patisiers',NULL,NULL),(8,'FAP','aromes poudres',1,2,1,'intrants patisiers',NULL,NULL),(9,'FCC','crémes chaudes',1,1,1,'crémes',NULL,NULL),(10,'FCF','crémes froides',1,1,1,'crémes',NULL,NULL),(11,'FDDS','decoration  de surface',1,2,1,'intrants patisiers',NULL,NULL),(12,'FDDS','decoration  de surface',1,2,1,'intrants patisiers',NULL,NULL),(13,'FF','fourrage',1,2,1,'intrants patisiers',NULL,NULL),(14,'FI','impérial',1,5,1,'préparation fromagère',NULL,NULL),(15,'FMZ','mamie zakia',1,5,1,'préparation fromagère',NULL,NULL),(16,'FM','mozzarel',1,5,1,'préparation fromagère',NULL,NULL),(17,'FŒLFB','œufs liquides frais blancs',1,3,1,'œufs liquides frais',NULL,NULL),(58,'TE','top che type edam',2,24,1,'top chef',NULL,NULL),(59,'TB','top chef',2,24,1,'top chef',NULL,NULL),(60,'TJ','top chef jaune',2,24,1,'top chef',NULL,NULL),(61,'TE','top chef type edam',2,24,1,'top chef',NULL,NULL),(4062,'GDM','ddddd0',0,NULL,0,NULL,NULL,NULL),(4163,'gmd','ddddd',0,NULL,0,NULL,NULL,NULL),(4164,'gmd','ddddd',0,NULL,0,NULL,NULL,NULL),(4165,'sss123','ssss123',0,NULL,0,NULL,NULL,NULL),(4169,'test-ajout-famille','test-ajout-famille-nom',0,NULL,0,NULL,NULL,NULL),(4200,'test-ajout-famille','test-ajout-famille-nom',0,NULL,0,NULL,NULL,NULL),(4201,'test-ajout-famille','test-ajout-famille-nom',0,NULL,0,NULL,NULL,NULL),(4202,'test-ajout-famille','test-ajout-famille-nom',0,NULL,0,NULL,NULL,NULL),(4991,'add-famm-test-edit','add-fam-test-edit',1,4202,0,NULL,NULL,NULL),(4992,'add-sous-fam-edited','add-sous-fam-edited',1,4202,0,NULL,NULL,NULL),(4993,'add-sf-1','add-sf-1',1,6,0,NULL,NULL,NULL),(4994,'add-sf-1220000000000000','add-sf-1220000',1,4062,0,NULL,NULL,NULL),(6014,'test add4','test add4',0,NULL,0,NULL,NULL,NULL),(6200,'88890','ddddd',1,5,0,NULL,NULL,NULL),(6201,'88890','ddddd',1,6,0,NULL,NULL,NULL),(6202,'88890555','ddddd',1,3,0,NULL,NULL,NULL),(6203,'sssssssss','sssss',1,4169,0,NULL,NULL,NULL),(6204,'88890','ddddd',1,6,0,NULL,NULL,NULL),(6205,'ch','ddddd',0,NULL,0,NULL,NULL,NULL),(6206,'88890 fayssal','imad 123456',0,NULL,0,NULL,NULL,NULL),(6207,'88890','ddddd',1,3,0,NULL,NULL,NULL),(6208,'88890','ddddd 123456',1,5,0,NULL,NULL,NULL),(6209,'88890','ABCD',0,NULL,0,NULL,NULL,NULL),(6210,'88890','ddddd',0,NULL,0,NULL,NULL,NULL),(6211,'88890','Créé',0,NULL,0,NULL,NULL,NULL),(6212,'123456','TEST SUP',0,NULL,0,NULL,NULL,NULL),(6213,'FENIX','SOUS CAT',0,NULL,0,NULL,NULL,NULL),(6214,'88890','ddddd',0,NULL,0,NULL,NULL,NULL),(6215,'88890','ddddd',0,NULL,0,NULL,NULL,NULL),(6216,'123','ddddd',0,NULL,0,NULL,NULL,NULL),(6217,'88890','ddddd',0,NULL,0,NULL,NULL,NULL),(6218,'88890','ddddd',1,5,0,NULL,NULL,NULL),(6219,'88890','ddddd',1,4200,0,NULL,NULL,NULL),(6220,'88890','Ramadan 2020',0,NULL,0,NULL,NULL,NULL),(6221,'88890','ddddd',0,NULL,0,NULL,NULL,NULL),(6222,'88890','ddddd',0,NULL,0,NULL,NULL,NULL),(6223,'486802','ZIZI MOHAMED',0,NULL,0,NULL,NULL,NULL),(6224,'arouma','arouma',2,7,1,NULL,NULL,NULL),(6225,'88890','Créé',0,NULL,1,NULL,NULL,NULL),(6226,'aa','Hello World',0,NULL,1,NULL,NULL,NULL),(6227,'bb','bb',0,NULL,0,NULL,NULL,NULL),(6237,'TEST','TEST CATEGORIE',0,NULL,0,NULL,NULL,NULL),(6238,'TESTSC','TEST SOUS CATEGORIE',1,6237,0,NULL,NULL,NULL),(6239,'TESTSC3','TEST DE CATEGORIE 3',1,6200,0,NULL,NULL,NULL),(6240,'AAAA','AAAAA',1,6225,1,NULL,NULL,NULL),(6241,'AAAA','AAAAA',2,6240,0,NULL,NULL,NULL),(6242,'AAAAB','AAAAB',0,NULL,0,NULL,NULL,NULL),(6243,'AAAAA','AAAAABB',1,6226,0,NULL,NULL,NULL),(6244,'AAAAA','AAAAAACC',1,6226,0,NULL,NULL,NULL),(6245,'AAAA','AAAABBCC 6246',0,NULL,0,NULL,NULL,NULL),(6246,'AAAA','AAAAAABB - 6246',0,NULL,0,NULL,NULL,NULL),(6247,'88890','Test Final',0,NULL,0,NULL,NULL,NULL),(6248,'88890','ddddd',1,4062,0,NULL,NULL,NULL),(6249,'88890555','ddddd',1,6248,0,NULL,NULL,NULL),(6250,'88890','123',1,4200,0,NULL,NULL,NULL),(6251,'AAAAAACC','ddddd123',1,6226,0,NULL,NULL,NULL),(6252,'AAAAAACC','AAAAAACC',1,6245,0,NULL,NULL,NULL),(6253,'8889056','ddddd 85662',2,24,1,NULL,NULL,NULL),(6254,'88890','child test final',1,6247,0,NULL,NULL,NULL),(6255,'ccccc','child 2 test final',1,6247,0,NULL,NULL,NULL),(6256,'xxxxxx','child of child test final ',2,6255,0,NULL,NULL,NULL),(6257,'88890','ddddd',2,6255,0,NULL,NULL,NULL),(6258,'88890','ddddd',2,6255,0,NULL,NULL,NULL),(6259,'88890','ddddd',2,6255,0,NULL,NULL,NULL),(6260,'88890','ddddd',2,24,1,NULL,NULL,NULL),(6261,'88890','ddddd',2,6254,0,NULL,NULL,NULL),(6354,'fnx','fenix',2,13,1,NULL,NULL,NULL);
/*!40000 ALTER TABLE `prds__classification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prds__element`
--

DROP TABLE IF EXISTS `prds__element`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prds__element` (
  `id` bigint NOT NULL,
  `value` varchar(255) NOT NULL,
  `element_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfr3i6ht0s8l91uugnwn5q9lhg` (`product_id`),
  KEY `FKbkoebdkyfviwcb18qy1l8wtxt` (`element_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prds__element`
--

LOCK TABLES `prds__element` WRITE;
/*!40000 ALTER TABLE `prds__element` DISABLE KEYS */;
/*!40000 ALTER TABLE `prds__element` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prds__formula`
--

DROP TABLE IF EXISTS `prds__formula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prds__formula` (
  `id` bigint NOT NULL,
  `obsolete` tinyint(1) DEFAULT '1',
  `active` tinyint(1) DEFAULT '1',
  `code` varchar(255) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `value` varchar(255) NOT NULL,
  `meta_data_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpqjdacnyvv1gw43w9j7x02f0g` (`meta_data_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prds__formula`
--

LOCK TABLES `prds__formula` WRITE;
/*!40000 ALTER TABLE `prds__formula` DISABLE KEYS */;
/*!40000 ALTER TABLE `prds__formula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prds__line`
--

DROP TABLE IF EXISTS `prds__line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prds__line` (
  `id` bigint NOT NULL,
  `active` tinyint(1) DEFAULT '1',
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prds__line`
--

LOCK TABLES `prds__line` WRITE;
/*!40000 ALTER TABLE `prds__line` DISABLE KEYS */;
/*!40000 ALTER TABLE `prds__line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prds__meta_data_value`
--

DROP TABLE IF EXISTS `prds__meta_data_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prds__meta_data_value` (
  `id` bigint NOT NULL,
  `active` tinyint(1) DEFAULT '1',
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `value` varchar(255) NOT NULL,
  `meta_data_id` bigint NOT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKigrj04t521nhnpqbnd750awqq` (`meta_data_id`),
  KEY `FK6qisj8qnwd0nbv410ob3nxxw` (`product_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prds__meta_data_value`
--

LOCK TABLES `prds__meta_data_value` WRITE;
/*!40000 ALTER TABLE `prds__meta_data_value` DISABLE KEYS */;
INSERT INTO `prds__meta_data_value` VALUES (7384,1,'2022-02-23 15:06:24',NULL,'qsqs',2,NULL),(7383,1,'2022-02-23 15:06:24',NULL,'sdqsdsq',1,NULL),(7389,1,'2022-02-23 15:52:01',NULL,'sdqsdsq',1,NULL),(7390,1,'2022-02-23 15:52:01',NULL,'qsqs',2,NULL);
/*!40000 ALTER TABLE `prds__meta_data_value` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prds__packaging`
--

DROP TABLE IF EXISTS `prds__packaging`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prds__packaging` (
  `id` bigint NOT NULL,
  `active` tinyint(1) DEFAULT '1',
  `name` varchar(255) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prds__packaging`
--

LOCK TABLES `prds__packaging` WRITE;
/*!40000 ALTER TABLE `prds__packaging` DISABLE KEYS */;
INSERT INTO `prds__packaging` VALUES (1,1,'fournisseur matière première',NULL,'2022-01-15 10:34:59'),(2,1,'bidon de 5 kg de capacité',NULL,NULL),(3,0,'ééééé',NULL,NULL),(4,1,'bouteille en plastique ',NULL,NULL),(5,1,'film de cire et de cellophane',NULL,NULL),(6,1,'film thermoformé',NULL,NULL),(7,1,'film thermoformé \n(nouveau produit)',NULL,NULL),(8,1,'film thermoformé \n(produit annulé de la gamme) \ndès le 24-06-21',NULL,NULL),(9,1,'film thermoformé \n(produit spécifique à cosamia)',NULL,NULL),(10,1,'film thermoformé  \n(produit spécifique à cosamia)',NULL,NULL),(11,1,'flacon en plastique',NULL,NULL),(12,1,'produit moulé',NULL,NULL),(13,1,'sac plastique etanche et seau en plastique',NULL,NULL),(14,1,'sac sous vide',NULL,NULL),(15,1,'sac sous vide \n(nouveau produit)',NULL,NULL),(16,1,'sac sous vide / \nfilm thermoformé',NULL,NULL),(17,1,'sac sous vide / film thermoformé (nouveau produit)',NULL,NULL),(18,1,'sac sous vide / filme thermoformé',NULL,NULL),(19,1,'seau + sac de 21 kg de capacité',NULL,NULL),(20,1,'seau de 10 kg',NULL,NULL),(21,1,'seau de 20 kg',NULL,NULL),(22,1,'seau des billes',NULL,NULL),(23,1,'seau des boules',NULL,NULL),(24,1,'seau en plastique',NULL,NULL),(4143,0,'wwww',NULL,NULL),(4142,0,'@@@@....----**///..//,nb%µ£$.s+r\'',NULL,NULL),(4144,0,'dd.0*-@@',NULL,NULL),(4145,0,'.*',NULL,NULL),(4146,0,'.',NULL,NULL),(4147,0,'',NULL,NULL),(4148,0,'',NULL,NULL),(4149,0,'@@@@@...d/*9+84ç',NULL,NULL),(4254,0,'test-ajout-famille-nom',NULL,NULL),(4999,0,'testtt',NULL,NULL),(5003,0,'111111111111',NULL,NULL),(6145,0,'ddddd',NULL,NULL),(6132,0,'Hello je suis la ',NULL,NULL),(6133,0,'Ramadan 2020',NULL,NULL),(6134,0,'Créé fenix',NULL,NULL),(6136,0,'fenix Benmoussa',NULL,NULL),(6137,0,'wwwww',NULL,NULL),(6138,0,'imad',NULL,NULL),(6139,0,'test emballage',NULL,NULL),(6140,0,'test emballage 298098098',NULL,NULL),(6143,0,'ssssss',NULL,NULL),(6144,0,'ddddd',NULL,NULL),(6146,0,'abcd',NULL,NULL),(6147,0,'admin',NULL,NULL),(6148,0,'Fayssal Benmoussacccc',NULL,NULL),(6149,1,'Ramadan 2020',NULL,NULL),(6150,0,'qqqq',NULL,NULL),(6355,1,'Test Fenix',NULL,NULL);
/*!40000 ALTER TABLE `prds__packaging` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prds__product`
--

DROP TABLE IF EXISTS `prds__product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prds__product` (
  `id` bigint NOT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `code_des` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `classification_id` bigint NOT NULL,
  `packaging_id` bigint NOT NULL,
  `production_unit_id` bigint NOT NULL,
  `product_type_id` bigint NOT NULL,
  `active` tinyint(1) DEFAULT '1',
  `si_unit_id` bigint DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe1r4855lopehidmeclhss6n7v` (`classification_id`),
  KEY `FK77poyoi8drbt960lana4x72k2` (`packaging_id`),
  KEY `FKl17me8wmrt49sa0r21ngx37cc` (`product_type_id`),
  KEY `FKmr4f6dw2lm8674rvr7f9phy3n` (`production_unit_id`),
  KEY `FKj5xsdktpoesp92dsu2nxdxybh` (`si_unit_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prds__product`
--

LOCK TABLES `prds__product` WRITE;
/*!40000 ALTER TABLE `prds__product` DISABLE KEYS */;
INSERT INTO `prds__product` VALUES (1,'IMP','Impérial Mix En Seau Petite  Format De 15 kg','Impérial Mix 15 kg',33,2,1,1,0,2,NULL,'2021-12-31 10:01:33',NULL),(2,'IMM','Impérial Mix  En Seau Moyenne Format De 5 kg','Impérial Mix 5 kg',34,24,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(3,'AFP','Aroma Plus Fruits passion En Bouteille De 1 kg','Aroma Plus Fruits passion 1 kg',25,4,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(4,'AAN','Aroma Plus Ananas  En Bouteille De 1 kg','Aroma Plus Ananas 1 kg',25,4,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(5,'ABA','Aroma Plus Banane     En Bouteille De 1 kg','Aroma Plus Banane 1 kg',25,4,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(6,'ACI','Aroma Plus Citron En Boutielle De 1 kg','Aroma Plus Citron 1 kg',25,4,1,1,1,1,NULL,'2022-01-14 09:33:41',NULL),(7,'ACV','Aroma Plus Citron Vert En Bouteille De 1 kg','Aroma Plus Citron Vert 1 kg',25,6355,1,1,1,1,NULL,'2021-12-23 08:08:33',NULL),(8,'AFS','Aroma Plus Fraise En Bouteille De 1 kg','Aroma Plus Fraise 1 kg',25,4,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(9,'AFM','Aroma Plus Framboise En Bouteille De 1 kg','Aroma Plus Framboise 1 kg',25,4,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(10,'AFR','Aroma Plus Fruits Rouge En Bouteille De 1 kg','Aroma Plus Fruits rouge 1 kg',25,4,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(11,'AAM','Aroma Plus Amande En Bouteille De 1 kg','Aroma Plus Amande 1 kg',26,4,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(12,'ANO','Aroma Plus NOUGAT En Bouteille De 1 kg','Aroma Plus NOUGAT 1 kg',26,4,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(13,'API','Aroma Plus PISTACHE En Bouteille De 1 kg','Aroma Plus PISTACHE 1 kg',26,4,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(16,'ACA','Aroma Plus Cafe En Bouteille De 1 kg','Aroma Plus Cafe 1 kg',29,4,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(17,'ACP','Aroma Plus Cappuccino En Bouteille De 1 kg','Aroma Plus Cappuccino 1 kg',29,4,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(18,'ACR','Aroma Plus Caramel En Bouteille De 1 kg','Aroma Plus Caramel 1 kg',29,4,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(19,'AMT','Aroma Plus Menthe En Bouteille De 1 kg','Aroma Plus Menthe 1 kg',29,4,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(20,'AVA','Aroma Plus Vanille En Bouteille De 1 kg','Aroma Plus Vanille 1 kg',29,4,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(21,'AYP','Aroma Plus Yaourt Poudre En Seau De 1 kg','Aroma Plus Yaourt Poudre 1 kg',47,24,1,1,1,1,NULL,'2021-12-30 10:05:55',NULL),(22,'AVP','Aroma Plus Vanille poudre En Seau De 1 kg','Aroma Plus Vanille poudre 1 kg',29,24,1,1,1,1,NULL,'2021-12-30 10:06:13',NULL),(23,'CCG ','Créme Cosamia En Seau Grande Format De 10 kg','Créma Cosamia 10 kg',31,24,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(24,'CCB','Créme Cosamia En Bidon Grande Format De 5 kg','Créma Cosamia 5 kg',31,3,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(25,'CDM','Créma Délice En Bidon Moyenne Format De 2 L','Créma délice 2 L',31,3,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(26,'CDG','Créma Délice En Bidon Grande Format De 5 L','Créma délice 5 L',31,3,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(27,'CIM','Créma Ice En Seau Moyenne Format De 5 kg','Créma Ice 5 L',32,24,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(28,'CFM','Créma Frais En Seau Moyenne Format De 48 kg','Créma Frais 5 L',33,24,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(29,'CPM','Créma Pâte En Seau Moyenne Format De 5 kg','Créma Pâte 5 kg',33,24,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(30,'CCM ','Créma Plus Classique En Seau Moyenne Format De 48 kg','Créma Plus Classique 5 L',33,13,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(31,'COM','Créma Plus Originale En Seau Moyenne Format De 48 kg','Créma Plus Originale 5 L',33,13,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(32,'IBM','Impérial Blanc En Seau Moyenne Format De 4 kg','Imperial Mirroir Blanc 4 kg',35,24,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(33,'ICM','Impérial Caramel En Seau Moyenne Format De 4 kg','Imperial Mirroir Caramel 4 kg',35,24,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(34,'IHM ','Impérial Chocolat En Seau Moyenne Format De 4 kg','Imperial Mirroir Chocolat 4 kg',35,24,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(35,'NNM','Nappage Neutre En Seau Moyenne Format De 43 kg','Top Chef Nappage Neutre  43 kg',45,24,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(36,'MDM','Sauce Moutarde Deliso\'s En Bidon Moyenne Format De 2 kg','Deliso\'s Sauce Moutarde 2 kg',55,3,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(37,'MXP','Sauces Moutarde Déliso\'s En Flacon Petite Format  De 300 gr','Deliso\'s Sauce Moutarde 300 gr',55,11,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(38,'MXG','Sauces Moutarde Déliso\'s En Flacon Grande Format  De 950 gr','Deliso\'s Sauce Moutarde 950 gr',55,11,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(39,'BXP','Sauces Barbecue Déliso\'s En Flacon Petite Format  De 300 gr','Déliso\'s Sauce Barbecue 300 gr',56,11,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(40,'BXG','Sauces Barbecue Déliso\'s En Flacon Grande Format  De 950 gr','Déliso\'s Sauce Barbecue 950 gr',56,11,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(41,'PDM','Sauce Piquante Deliso\'s En Bidon Moyenne Format De 2 kg','Deliso\'s Sauce Piquante 2 kg',56,3,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(42,'PXP','Sauces Piquante Déliso\'s En Flacon Petite Format  De 300 gr','Deliso\'s Sauce Piquante 300 gr',56,11,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(43,'PXG','Sauces Piquante Déliso\'s En Flacon Grande Format  De 950 gr','Deliso\'s Sauce Piquante 950 gr',56,11,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(44,'ZDG','Sauce Pizza Déliso\'s En Bidon Grande Format De 5 kg','Deliso\'s Pizza 5 kg',56,3,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(45,'KXP','Sauce Ketchup Déliso\'s En Flacon Petite Format De 300 gr','Deliso\'s Sauce Ketchup 300 gr',57,11,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(46,'KXG','Sauce Ketchup Déliso\'s En Flacon Grande Format De 950 gr','Deliso\'s Sauce Ketchup 950 gr',57,11,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(47,'KDM ','Sauce Ketchup Déliso\'s En Bidon Moyenne Format De 2 kg','Deliso\'s Sauce  Ketchup 2 kg',57,3,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(48,'KDG','Sauce Ketchup Déliso\'s En Bidon Grande Format De 5 kg','Deliso\'s Sauce Ketchup 5 kg',57,3,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(49,'GXP','Sauces Algérienne Déliso\'s En Flacon Petite Format De 300 gr','Deliso\'s  Sauce Algérienne 300 gr',53,11,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(50,'GDM','Sauces Algérienne Déliso\'s En Seau Moyenne Format  De 47 kg','Deliso\'s Sauce Algérienne 47 kg',53,24,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(51,'GXG','Sauces Algérienne Déliso\'s En Flacon Grande Format  De 950 gr','Deliso\'s Sauce Algérienne 950 gr',53,11,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(52,'AXP','Sauces Andalouse Déliso\'s En Flacon Petite Format De 300 gr','Deliso\'s Sauce Andalouse 300 gr',53,11,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(53,'ADM','Sauces Andalouse Déliso\'s En Seau Moyenne Format  De 47 kg','Deliso\'s Sauce Andalouse 47 kg',53,24,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(54,'AXG','Sauces Andalouse Déliso\'s En Flacon Grande Format  De 950 gr','Deliso\'s Sauce Andalouse 950 gr',53,11,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(55,'RXP','Sauces Biggy Burger Déliso\'s En Flacon Petite Format De 300 gr','Deliso\'s Sauce Biggy Burger 300 gr',53,11,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(56,'RDM','Sauces Biggy Burger Déliso\'s En Seau Moyenne Format  De 47 kg','Deliso\'s Sauce Biggy Burger 47 kg',53,24,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(57,'RXG','Sauces Biggy Burger Déliso\'s En Flacon Grande Format  De 950 gr','Deliso\'s Sauce Biggy Burger 950 gr',53,11,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(58,'CXP','Sauces Cheezy Déliso\'s En Flacon Petite Format De 300 gr','Deliso\'s Sauce Cheezy 300 gr',53,11,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(59,'CXG','Sauces Cheezy Déliso\'s En Flacon Grande Format  De 950 gr','Deliso\'s Sauce Cheezy 950 gr',53,11,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(60,'TXP','Sauces Pita Déliso\'s En Flacon Petite Format De 300 gr','Deliso\'s Sauce Pita 300 gr',53,11,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(61,'TXG','Sauces Pita Déliso\'s En Flacon Grande Format  De 950 gr','Deliso\'s Sauce Pita 950 gr',53,11,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(62,'SXP','Sauces Samourai Déliso\'s En Flacon Petite Format De 300 gr','Deliso\'s Sauce Samourai 300 gr',53,11,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(63,'SXG','Sauces Samourai Déliso\'s En Flacon Grande Format  De 950 gr','Deliso\'s Sauce Samourai 950 gr',53,11,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(64,'YDG','Sauces Mayonnaise Déliso\'s En Bidon Grande Format  De 2 kg','Deliso\'s Sauce Mayonnaise 2 kg',54,3,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(65,'YXP','Sauces Mayonnaise Déliso\'s En Flacon Petite Format  De 300 kg','Deliso\'s Sauce Mayonnaise 300 gr',41,11,1,1,1,1,NULL,'2021-12-23 09:39:02',NULL),(66,'YXG','Sauces Mayonnaise Déliso\'s En Flacon Grande Format  De 950 kg','Deliso\'s Sauce Mayonnaise 950 gr',54,11,1,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(67,'IMV','Impérial Mix En Seau De 20 kg','Impérial Mix Vrac',34,21,1,2,1,1,NULL,'2022-01-18 14:06:56',NULL),(68,'FPV','Aroma Plus Fruits passion En Seau De 10 kg','Aroma Plus Fruits passion Vrac',25,20,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(69,'ANV','Aroma Plus Ananas  En Seau De 10 kg','Aroma Plus Ananas Vrac',25,20,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(70,'BAV','Aroma Plus Banane En Seau De 10 kg','Aroma Plus Banane Vrac',25,20,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(71,'CVV','Aroma Plus Citron Vert En Seau De 10 kg','Aroma Plus Citron Vert Vrac',25,5,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(72,'CIV','Aroma Plus Citron En Seau De 10 kg','Aroma Plus Citron Vrac',25,20,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(73,'FSV','Aroma Plus Fraise En Seau De 10 kg','Aroma Plus Fraise Vrac',25,20,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(74,'FMV','Aroma Plus Framboise En Seau De 10 kg','Aroma Plus Framboise Vrac',25,20,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(75,'FRV','Aroma Plus Fruits Rouge En Seau De 10 kg','Aroma Plus Fruits rouge Vrac',25,20,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(76,'AMV','Aroma Plus Amande En Seau De 10 kg','Aroma Plus Amande Vrac',26,12,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(77,'NOV','Aroma Plus NOUGAT En Seau De 10 kg','Aroma Plus NOUGAT Vrac',26,20,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(79,'BEV','Aroma Plus Beurre En Seau  DE 10 kg','Aroma Plus Beurre Vrac',27,20,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(80,'BUV','Aroma Plus Bubbl GUM En Seau De 10 kg','Aroma Plus Bubbl GUM Vrac',27,20,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(81,'CAV','Aroma Plus Cafe En Seau De 10 kg','Aroma Plus Cafe Vrac',29,20,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(82,'CPV','Aroma Plus Cappuccino En Seau De 10 kg','Aroma Plus Cappuccino Vrac',29,20,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(83,'CRVDFDF','Aroma Plus Caramel En Seau De 10 kg','Aroma Plus Caramel Vrac',29,11,2,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(84,'MTV','Aroma Plus Menthe En Seau De 10 kg','Aroma Plus Menthe Vrac',29,20,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(85,'VAV','Aroma Plus Vanille En Seau De 10 kg','Aroma Plus Vanille Vrac',29,20,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(86,'YPV','Aroma Plus Yaourt Poudre En Seau De 10 kg','Arome Plus Yaourt Poudre Vrac',27,20,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(87,'VPV','Aroma Plus Vanille poudre En Seau De 10 kg','Arome Plus Vanille poudre Vrac',29,20,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(88,'CVC','Créma Cosamia Vrac En Seau De 20 kg','Créma Cosamia Vrac',31,21,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(89,'CDV','Créma Délice Vrac En Seau De 20 kg','Créma délice Vrac',31,21,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(90,'CIV','Aroma Plus Citron En Seau De 10 kg','Aroma Plus Citron Vrac',32,21,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(91,'CFV','Créma Frais Vrac En Seau De 20 kg','Créma Frais Vrac',33,21,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(92,'CPV','Aroma Plus Cappuccino En Seau De 10 kg','Aroma Plus Cappuccino Vrac',33,21,1,2,1,1,NULL,'2022-01-19 14:21:18',NULL),(93,'CCV','Créma Classique Vrac En Seau De 20 kg','Créma Plus Classique Vrac',33,21,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(94,'COV','Créma Originale Vrac En Seau De 20 kg','Créma Plus Originale Vrac',33,21,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(95,'IBV','Impérial Blanc En Seau De 20 kg','Imperial Mirroir Blanc Vrac',35,21,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(96,'ICV','Impérial Caramel En Seau De 20 kg','Imperial Mirroir Caramel Vrac',35,21,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(97,'IHV','Impérial Chocolat En Seau De 20 kg','Imperial Mirroir Chocolat Vrac',35,21,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(98,'NNV  ','Nappage Neutre En Seau De 20 kg','Top Chef Nappage Neutre Vrac',45,21,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(99,'SMV','Sauce Moutard En Seau De 20 kg','Deliso\'s Sauce Moutarde Vrac',55,21,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(100,'SBV','Sauce Barbecue En Seau De 20 kg','Déliso\'s Sauce Barbecue Vrac',56,21,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(101,'SPV','Sauce Piquante En Seau De 20 kg','Deliso\'s Sauce Piquante Vrac',56,21,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(102,'SZV','Sauce Pizza En Seau De 20 kg','Deliso\'s Sauce Pizza Vrac',56,21,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(103,'SKV ','Sauce Ketchup En Seau De 20 kg','Deliso\'s Sauce Ketchup Vrac',57,21,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(104,'SGV','Sauce Algérienne En Seau De 20 kg','Deliso\'s Sauce Algérienne Vrac',53,21,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(105,'SAV','Sauce Andalouse En Seau De 20 kg','Deliso\'s Sauce Andalouse Vrac',53,21,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(106,'SRV','Sauce Biggy Burger En Seau De 20 kg','Deliso\'s Sauce Biggy Burger Vrac',53,21,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(107,'SCV','Sauce Cheezy En Seau De 20 kg','Deliso\'s Sauce Cheezy Vrac',53,21,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(108,'STV','Sauce Pita En Seau De 20 kg','Deliso\'s Sauce Pita Vrac',53,21,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(109,'SSV','Sauce Samourai En Seau De 20 kg','Deliso\'s Sauce Samourai Vrac',53,21,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(110,'SYV ','Sauce Mayonnaise En Seau De 20 kg','Deliso\'s Sauce Mayonnaise Vrac',54,21,1,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(111,'IEP','Impérial Entier Petite Format En Film de Cire Et De Cellophane','Boule Impérial 09 kg',37,5,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(112,'IEM','Impérial Entier Moyenne Format En Film de Cire Et De Cellophane','Boule Impérial 1 5 kg',37,5,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(113,'IEG','Impérial Entier Grande Format En Film de Cire Et De Cellophane','Boule Impérial 1 7 kg',37,5,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(114,'IPP','Impérial Portion Petite Format En Sac Sous Vide Indivuduel de 100 gr','Portion Impérial 100 gr',37,14,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(115,'IPG ','Impérial Portion Grande Format En Sac Sous Vide Indivuduel de 100 gr','Portion Impérial 170 gr',37,14,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(116,'MBI ','Mamie Zakia Billes En Seau Plastique Etanche De 1 kg','Mamie Zakia Salade Billes 1 kg',41,22,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(117,'MBO','Mamie Zakia Boules En Seau Plastique Etanche De 1 kg','Mamie Zakia Salade Boules 1 kg',41,23,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(118,'MRG','Mamie Zakia Râpé Grande Format En Sac Sous Vide De 1 kg','Mamie Zakia  Râpé 1 kg',39,14,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(119,'MBG','Mamie Zakia Bloc Grande Format En Film Thermoformé Sous Vide  De 1 kg','Mamie Zakia Bloc 1 kg',39,6,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(120,'MBG','Mamie Zakia Bloc Grande Format En Film Thermoformé Sous Vide  De 1 kg','Mamie Zakia Bloc 1 kg',42,6,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(121,'MPM','Mozzarel Portion Moyenne Format En Film Thermoformé Sous Vide De 200 gr','Mozzarel Portion 200 gr',42,6,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(122,'MPP','Mozzarel Portion Petite Format En Sac Sous Vide Individuel De 90 gr','Mozzarel Portion 90 gr',42,16,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(123,'MRG','Mamie Zakia Râpé Grande Format En Sac Sous Vide De 1 kg','Mamie Zakia  Râpé 1 kg',42,14,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(124,'MRP','Mozzarel Râpé Petite Format En Sac Sous Vide De 200 gr','Mozzarel Râpé 200 gr',42,15,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(125,'MJB ','Mozzarel Jaune Bloc Grande Format En Film Thermoformé Sous Vide  De 1 kg','Mozzarel Jaune Bloc  1 kg',43,6,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(126,'MJR ','Mozzarel Jaune Râpé Grande Format En Sac Sous Vide De 1 kg','Mozzarel Jaune Râpé 1 kg',43,14,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(127,'TRP','Top Chef Râpé Petite Format En Sac Sous Vide De 250 gr','Top Chef  Râpé 250 gr',59,14,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(128,'TBG','Top Chef Bloc Grande Format En Film Thermoformé Sous Vide  De 1 kg','Top Chef Bloc 1 kg',59,6,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(129,'TBC','Top Chef Cosamia Blanche Bloc Grande Format En Film Thermoformé Sous Vide  De 1 kg','Top Chef Cosamia Bloc 1 kg',59,9,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(130,'TPP','Top Chef Portion Petite Format En Sac Sous Vide Individuel De 100 gr','Top Chef Portion 100 gr',59,18,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(131,'TPG','Top Chef Portion Grande Format En Film Thermoformé Sous Vide De 250 gr','Top Chef Portion 250 gr',59,6,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(132,'TPC','Top Chef Portion Carré Grande Format En Film Thermoformé Sous Vide De 250 gr','Top Chef Portion Carré 250 gr',59,8,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(133,'TRG','Top Chef Râpé Grande Format En Sac Sous Vide De 1 kg','Top Chef Râpé 1 kg',59,14,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(134,'TJC ','Top Chef Cosamia Jaune Bloc Grande Format En Film Thermoformé Sous Vide  De 1 kg','Top chef Cosamia Jaune Bloc 1 kg',60,10,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(135,'TJB ','Top Chef Jaune Bloc Grande Format En Film Thermoformé Sous Vide  De 1 kg','Top Chef Jaune Bloc 1 kg',60,6,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(136,'TJR','Top Chef Jaune Râpé Grande Format En Sac Sous Vide De 1 kg','Top Chef Jaune Râpé 1 kg',60,14,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(137,'EBG','Edam Bloc Grande Format En Film Thermoformé Sous Vide  De 1 kg','Top Chef Edam Bloc 1 kg',61,14,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(138,'EPP','Edam portion Petite Format En Sac Sous Vide Individuel De 100 gr','Top Chef Edam Portion 100 gr',61,17,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(139,'EPG','Edam Portion Grande Format En Film Thermoformé Sous Vide De 250 gr','Top Chef Edam Portion 250 gr',61,7,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(140,'ERG','Edam Râpé Grande Format En Sac Sous Vide De 1 kg','Top Chef Edam Râpé 1 kg',61,14,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(141,'ERP','Edam Râpé Petite Format En Sac Sous Vide De 250 gr','Top Chef Edam Râpé 250 gr',61,15,2,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(142,'BIPP','Boule Impérial Petite Format De 09 Kg','Boule Impérial 09 kg',38,12,2,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(143,'BIPM','Boule Impérial Moyenne Format De 15 kg','Boule Impérial 1 5 kg',38,12,2,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(144,'BIPG','Boule Impérial Grande Format De 17 kg','Boule Impérial 17 kg',38,12,2,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(145,'PIPP','Portion Impérial Petite Format De 100 gr','Portion  Impérial 010 kg',38,12,2,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(146,'PIPG','Portion Impérial Grande Format De 170 gr','Portion Impérial 017 kg',38,12,2,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(147,'BSMG','Bloc Semi-Fini De Mamie Zakia Blanche Grande Format En Piéce de 1 kg','Bloc Mamie Zakia  1 kg',40,12,2,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(148,'BIMS','Billes De Mamie Zakia En Seau De 1 kg','Billes Mamie Zakia Salade 1 kg',41,12,2,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(149,'BOMS','Boules De Mamie Zakia En Seau De 1 kg','Boules Mamie Zakia Salade 1 kg',41,12,2,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(150,'BSMJ ','Bloc Semi-Fini De Mozzarel Jaune Grande Format En Piéce De 1 kg','Bloc Mozzarel Jaune 1 kg',44,12,2,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(151,'BSMG','Bloc Semi-Fini De Mamie Zakia Blanche Grande Format En Piéce de 1 kg','Bloc Mamie Zakia  1 kg',42,12,2,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(152,'BSMP ','Bloc Semi-Fini De Mozzarel Blanche Pour Portions En Piéce de 11 kg','Bloc Mozzarel 11 kg',42,12,2,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(153,'BSEG','Bloc Semi-Fini De Edam Grande Format En Piéce de 1 kg','Bloc Top Chef Edam 1 kg',58,12,2,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(154,'BSEP','Bloc Semi-Fini De Edam Pour Portions En Piéce de 11 kg','Bloc Top Chef Edam 11 kg',58,12,2,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(155,'BSTG','Bloc Semi-Fini De Top Chef Blanche Grande Format En Piéce de 1 kg','Bloc Top Chef  1 kg',59,12,2,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(156,'BSTP','Bloc Semi-Fini De Top Chef Blanche Pour Portions En Piéce de 11 kg','Bloc Top Chef 11 kg',59,12,2,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(157,'BSTC','Bloc Semi-Fini De Top Chef Blanche Carré En Piéce de 13 kg','Bloc Top Chef 13 kg',59,12,2,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(158,'BSTJ','Bloc Semi-Fini De Top Chef Jaune Grande Format En Piéce De 1 kg','Bloc Top Chef Jaune 1 kg',60,12,2,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(159,'F02','Blancs D\'oeuf  Frais En Bidon De 2 kg','Œufs Liquides Frais Blancs 2 kg',47,1,3,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(160,'F05','Blancs D\'oeuf  Frais En Bidon De 5 kg','Œufs Liquides Frais Blancs 5 kg',47,2,3,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(161,'B20','Blancs D\'œufs Pasteurisés En Sac Soudé De 20 kg','Œufs Liquides Pasteurisés Blancs 20 kg',48,19,3,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(162,'E10','Entiers D\'œufs Pasteurisés En Sac Soudé De 10 kg','Œufs Liquides Pasteurisés Entiers 10 kg',50,19,3,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(163,'E15','Entiers D\'œufs Pasteurisés En Sac Soudé De 15 kg','Œufs Liquides Pasteurisés Entiers 15 kg',50,19,3,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(164,'E16','Entiers D\'œufs Pasteurisés En Sac Soudé De 16 kg','Œufs Liquides Pasteurisés Entiers 16 kg',50,19,3,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(165,'E17','Entiers D\'œufs Pasteurisés En Sac Soudé De 17 kg','Œufs Liquides Pasteurisés Entiers 17 kg',50,19,3,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(166,'E02','Entiers D\'œufs Pasteurisés En Bidons De  2 kg','Œufs Liquides Pasteurisés Entiers 2 kg',50,1,3,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(167,'E20','Entiers D\'œufs Pasteurisés En Sac Soudé De 20 kg','Œufs Liquides Pasteurisés Entiers 20 kg',50,19,3,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(168,'E21','Entiers D\'œufs Pasteurisés En Sac Soudé De 21 kg','Œufs Liquides Pasteurisés Entiers 21 kg',50,19,3,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(169,'E05','Entiers D\'œufs Pasteurisés En Bidons De 5 kg','Œufs Liquides Pasteurisés Entiers 5 kg',50,2,3,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(170,'J02','Jaunes D\'œufs Pasteurisés En Bidons De 2 kg','Œufs Liquides Pasteurisés Jaunes 2 kg',51,1,3,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(171,'J20','Jaunes D\'œufs Pasteurisés En Sac Soudé De 20 kg','Œufs Liquides Pasteurisés Jaunes 20 kg',51,19,3,1,1,1,'2020-01-01 00:00:00',NULL,NULL),(172,'J05','Jaunes D\'œufs Pasteurisés En Bidons De 5 kg','Œufs Liquides Pasteurisés Jaunes 5 kg',51,6355,1,1,1,1,NULL,'2021-12-23 07:56:12',NULL),(173,'BVF','Blancs Vrac D\'œufs Liquides Frais En Seau De 20 kg','Œufs Liquides Frais Blancs Vracs',47,21,3,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(174,'MVF','Melanges Vrac De Blancs D\'Œufs Liquides Frais En Seau De 20 kg','Œufs Liquides Frais Melange Vracs',52,21,3,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(175,'BVP','Blancs Vrac D\'œufs Liquides Pasteurisés En Seau De 20 kg','Œufs Liquides Pasteurisés Blancs Vracs',48,21,3,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(176,'EVP','Entiers Vrac D\'oeufs Liquides Pasteurisés En Seau De 20 kg','Œufs Liquides Pasteurisés Entiers Vracs',49,21,3,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(177,'JVP','Jaunes Vrac D\'œufs Liquides Pasteurisés En Seau De 20 kg','Œufs Liquides Pasteurisés Jaunes Vracs',51,21,3,2,1,1,'2020-01-01 00:00:00',NULL,NULL),(7382,'qsqs qsqsqs','qsqsqs','fayssal dddenmoussa',38,19,2,3,1,8,'2022-02-23 15:06:24',NULL,NULL),(7385,'qsqs qsqsqs','qsqsqs','fayssal dddenmoussa',38,19,2,3,1,8,'2022-02-23 15:12:56',NULL,NULL),(7388,'qsqs qsqsqs','qsqsqs','fayssal dddenmoussa',38,19,2,3,1,8,'2022-02-23 15:52:01',NULL,NULL);
/*!40000 ALTER TABLE `prds__product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prds__production_unit`
--

DROP TABLE IF EXISTS `prds__production_unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prds__production_unit` (
  `id` bigint NOT NULL,
  `active` tinyint(1) DEFAULT '1',
  `name` varchar(255) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prds__production_unit`
--

LOCK TABLES `prds__production_unit` WRITE;
/*!40000 ALTER TABLE `prds__production_unit` DISABLE KEYS */;
INSERT INTO `prds__production_unit` VALUES (1,1,'Frais Caprices I',NULL,NULL),(2,1,'Frais Caprices II',NULL,NULL),(3,1,'Ovofrais',NULL,NULL),(4,1,'Commun Fabrication\n',NULL,NULL);
/*!40000 ALTER TABLE `prds__production_unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prds__si_unit`
--

DROP TABLE IF EXISTS `prds__si_unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prds__si_unit` (
  `id` bigint NOT NULL,
  `active` tinyint(1) DEFAULT '1',
  `name` varchar(255) NOT NULL,
  `quantity_name` varchar(255) NOT NULL,
  `symbol` varchar(255) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prds__si_unit`
--

LOCK TABLES `prds__si_unit` WRITE;
/*!40000 ALTER TABLE `prds__si_unit` DISABLE KEYS */;
INSERT INTO `prds__si_unit` VALUES (3,1,'mètre','longueur','m',NULL,NULL),(2,1,'kilogramme','masse','kg',NULL,NULL),(1,1,'unité','quantité','u',NULL,NULL),(4,1,'minute','temps','min',NULL,NULL),(5,1,'litre','volume','l',NULL,NULL),(6,1,'gramme','masse','g',NULL,NULL),(7,1,'millilitre','volume','ml',NULL,NULL),(8,1,'heure','temps','h',NULL,NULL),(6177,0,'ddddd','Masse','kg',NULL,NULL),(6178,0,'ddddd','Volume','CM',NULL,NULL),(6181,0,'Ramadan 2020','Volume','kg',NULL,NULL),(6182,0,'Ramadan 2020','Quantité','CM',NULL,NULL),(6184,0,'wwwww','Temps','CM',NULL,NULL),(6185,0,'Ramadan 2020','Volume','122313321',NULL,NULL),(6186,0,'ddddd1111','Quantité','kg111',NULL,NULL),(6187,0,'ddddd','quantité','kg',NULL,NULL),(6188,0,'gramme','Masse','g',NULL,NULL),(6193,0,'Unité','Quantité','U',NULL,NULL);
/*!40000 ALTER TABLE `prds__si_unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prds__type`
--

DROP TABLE IF EXISTS `prds__type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prds__type` (
  `id` bigint NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `active` tinyint(1) DEFAULT '1',
  `icon` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prds__type`
--

LOCK TABLES `prds__type` WRITE;
/*!40000 ALTER TABLE `prds__type` DISABLE KEYS */;
INSERT INTO `prds__type` VALUES (1,'Produits Finis',1,'box',NULL,NULL),(2,'Produits SemiFinis',1,'box-open',NULL,NULL),(3,'Matière première',1,'truck-loading',NULL,NULL),(4,'X produit de laboratoire',1,'box',NULL,'2022-01-15 10:34:35'),(5,'X produits d’entretien',1,'box',NULL,NULL),(7,'X Produits de maintenance industrielle',1,'box',NULL,NULL);
/*!40000 ALTER TABLE `prds__type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prds__type_metadata`
--

DROP TABLE IF EXISTS `prds__type_metadata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prds__type_metadata` (
  `product_type_id` bigint NOT NULL,
  `metadata_id` bigint NOT NULL,
  KEY `FKcymt310aeld6au1frqhdidmxa` (`metadata_id`),
  KEY `FK27rn8tcr2k7snihugdwxfbicu` (`product_type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prds__type_metadata`
--

LOCK TABLES `prds__type_metadata` WRITE;
/*!40000 ALTER TABLE `prds__type_metadata` DISABLE KEYS */;
INSERT INTO `prds__type_metadata` VALUES (3,1),(3,2);
/*!40000 ALTER TABLE `prds__type_metadata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sc__activity`
--

DROP TABLE IF EXISTS `sc__activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sc__activity` (
  `id` bigint NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `priority` varchar(255) DEFAULT NULL,
  `referer` varchar(255) DEFAULT NULL,
  `user_agent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKalbetul30dfdt329jq36llkrs` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sc__activity`
--

LOCK TABLES `sc__activity` WRITE;
/*!40000 ALTER TABLE `sc__activity` DISABLE KEYS */;
INSERT INTO `sc__activity` VALUES (1050,'2021-06-08 14:37:21','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36'),(1051,'2021-06-08 14:37:24','0:0:0:0:0:0:0:1',2,'The user fenix has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36'),(1052,'2021-06-08 14:37:37','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenix1. Reason: Bad credentials','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36'),(1053,'2021-06-08 14:37:42','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36'),(1054,'2021-06-08 14:44:03','0:0:0:0:0:0:0:1',2,'The user fenix has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36'),(1055,'2021-06-08 14:44:09','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36'),(1056,'2021-06-08 14:44:13','0:0:0:0:0:0:0:1',2,'The user fenix has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36'),(1057,'2021-06-08 14:44:19','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36'),(1058,'2021-06-21 08:07:29','172.17.0.1',NULL,'Failed login attempt with username : fayssal.zf@gmail.com. Reason: Bad credentials','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36'),(1059,'2021-06-21 08:07:36','172.17.0.1',NULL,'Failed login attempt with username : fayssal.zf@gmail.com. Reason: Bad credentials','WARN','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36'),(1060,'2021-06-21 08:13:09','172.17.0.1',NULL,'Failed login attempt with username : fayssal.zf@gmail.com. Reason: Bad credentials','WARN','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36'),(1061,'2021-06-21 08:13:10','172.17.0.1',NULL,'Failed login attempt with username : fayssal.zf@gmail.com. Reason: Bad credentials','WARN','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36'),(1062,'2021-06-21 14:56:37','105.155.251.128',NULL,'Failed login attempt with username : admin. Reason: Bad credentials','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36'),(1063,'2021-06-22 11:05:50','105.155.251.128',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36'),(1066,'2021-06-22 11:41:05','105.155.251.128',NULL,'Failed login attempt with username : issam. Reason: User is disabled','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36'),(1067,'2021-06-22 11:41:59','105.155.251.128',1065,'The user issam has logged in.','INFO','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36'),(1085,'2021-06-23 08:19:08','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36'),(1087,'2021-06-23 08:19:24','192.168.3.59',1065,'The user issam has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36'),(1090,'2021-06-23 08:32:33','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36'),(1091,'2021-06-23 08:33:30','0:0:0:0:0:0:0:1',2,'The user fenix has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36'),(1092,'2021-06-23 08:52:38','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36'),(1093,'2021-06-23 08:54:00','0:0:0:0:0:0:0:1',2,'The user fenix has logged out','INFO','/business/team/index','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36'),(1094,'2021-06-23 09:11:36','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36'),(1108,'2021-07-13 08:01:02','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1109,'2021-07-13 08:13:36','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1111,'2021-07-13 09:20:45','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1112,'2021-07-13 09:41:02','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1151,'2021-07-16 08:02:20','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1152,'2021-07-16 08:13:24','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1154,'2021-07-16 09:26:50','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1155,'2021-07-16 09:27:08','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1156,'2021-07-16 09:31:27','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1157,'2021-07-16 09:31:29','0:0:0:0:0:0:0:1',2,'The user fenix has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1158,'2021-07-16 09:34:42','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1159,'2021-07-16 09:45:40','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1161,'2021-07-16 09:48:33','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1162,'2021-07-16 09:52:47','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1163,'2021-07-16 09:54:50','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1164,'2021-07-16 09:56:18','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1165,'2021-07-16 09:58:29','127.0.0.1',NULL,'Failed login attempt with username : . Reason: Bad credentials','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1166,'2021-07-16 09:58:35','127.0.0.1',2,'The user fenix has logged in.','INFO','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1167,'2021-07-16 10:00:15','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1168,'2021-07-16 10:01:21','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Linux; Android 6.0.1; Moto G (4)) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Mobile Safari/537.36'),(1169,'2021-07-16 10:02:19','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1170,'2021-07-16 10:03:47','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1171,'2021-07-16 10:04:09','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1172,'2021-07-16 10:05:37','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1173,'2021-07-16 10:06:58','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1174,'2021-07-16 10:09:26','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1175,'2021-07-16 10:10:47','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1176,'2021-07-16 10:12:01','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1177,'2021-07-16 10:14:32','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1178,'2021-07-16 10:15:33','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1179,'2021-07-16 10:17:07','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1180,'2021-07-16 10:19:48','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1181,'2021-07-16 10:21:13','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1182,'2021-07-16 10:26:14','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1183,'2021-07-16 10:32:50','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1184,'2021-07-16 10:35:50','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1185,'2021-07-16 10:37:49','127.0.0.1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1186,'2021-07-16 10:42:42','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1188,'2021-07-16 10:44:03','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1189,'2021-07-16 10:46:37','127.0.0.1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1190,'2021-07-16 10:48:19','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1191,'2021-07-16 10:50:26','127.0.0.1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1192,'2021-07-16 10:55:01','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1193,'2021-07-16 10:57:27','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1194,'2021-07-16 11:11:49','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1196,'2021-07-16 13:36:23','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1197,'2021-07-16 13:36:43','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Linux; Android 6.0.1; Moto G (4)) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Mobile Safari/537.36'),(1201,'2021-07-16 14:00:41','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1202,'2021-07-16 14:03:42','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1203,'2021-07-16 14:06:47','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1204,'2021-07-16 14:13:24','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1206,'2021-07-16 14:22:44','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1207,'2021-07-16 14:22:47','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1208,'2021-07-16 14:24:58','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1209,'2021-07-16 14:28:18','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1210,'2021-07-16 14:31:22','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1211,'2021-07-16 14:33:04','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1212,'2021-07-16 14:34:42','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1213,'2021-07-16 14:36:14','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1214,'2021-07-16 14:37:20','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1215,'2021-07-16 14:41:53','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1216,'2021-07-16 14:43:11','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1217,'2021-07-16 14:45:51','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1219,'2021-07-16 14:47:29','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1220,'2021-07-16 14:50:40','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1223,'2021-07-16 15:02:17','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1224,'2021-07-16 15:21:13','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1225,'2021-07-16 15:23:09','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1226,'2021-07-16 15:34:34','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1227,'2021-08-02 08:57:35','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenixxx. Reason: User is disabled','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1228,'2021-08-02 08:57:40','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenix. Reason: Bad credentials','WARN','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1229,'2021-08-02 08:57:45','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenix. Reason: Bad credentials','WARN','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1230,'2021-08-02 08:57:50','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenix. Reason: Bad credentials','WARN','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1231,'2021-08-02 08:58:16','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenixxx. Reason: User is disabled','WARN','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1232,'2021-08-02 08:58:27','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenix. Reason: Bad credentials','WARN','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1233,'2021-08-02 08:58:38','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenix. Reason: Bad credentials','WARN','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1234,'2021-08-02 08:58:47','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1238,'2021-08-02 10:36:49','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1242,'2021-08-02 13:08:33','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1243,'2021-08-03 07:33:03','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1246,'2021-08-03 08:18:58','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1248,'2021-08-03 13:16:01','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1249,'2021-08-04 09:36:43','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1279,'2021-08-07 08:29:51','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenix. Reason: Bad credentials','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36'),(1280,'2021-08-07 08:29:57','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36'),(1283,'2021-08-09 08:57:20','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1284,'2021-08-09 08:59:16','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36'),(1285,'2021-08-09 09:08:41','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36'),(1286,'2021-08-09 09:12:28','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1287,'2021-08-09 09:16:00','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36'),(1288,'2021-08-09 09:22:07','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36'),(1290,'2021-08-09 09:58:17','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1291,'2021-08-09 15:38:02','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36'),(1292,'2021-08-11 07:29:27','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1294,'2021-08-12 10:07:25','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1295,'2021-08-12 11:07:30','127.0.0.1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36'),(1296,'2021-08-12 11:12:37','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1297,'2021-08-13 07:37:50','192.168.3.59',NULL,'Failed login attempt with username : issam. Reason: Bad credentials','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36'),(1298,'2021-08-13 07:37:54','192.168.3.59',1065,'The user issam has logged in.','INFO','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36'),(1307,'2021-08-16 08:36:05','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36'),(1315,'2021-08-16 08:38:39','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36'),(1559,'2021-08-23 07:46:04','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1660,'2021-08-24 07:51:44','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1697,'2021-08-24 10:29:10','192.168.3.4',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36'),(1704,'2021-08-24 10:42:45','127.0.0.1',NULL,'Failed login attempt with username : fenix. Reason: Bad credentials','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1705,'2021-08-24 10:42:54','127.0.0.1',2,'The user fenix has logged in.','INFO','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1706,'2021-08-24 10:43:02','127.0.0.1',2,'The user fenix has logged out','INFO','/security/index','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1707,'2021-08-24 10:43:21','127.0.0.1',1696,'The user medzizi has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1711,'2021-08-24 10:44:12','0:0:0:0:0:0:0:1',1696,'The user medzizi has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1712,'2021-08-24 10:44:16','0:0:0:0:0:0:0:1',1696,'The user medzizi has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1722,'2021-08-24 10:46:35','0:0:0:0:0:0:0:1',1696,'The user medzizi has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1724,'2021-08-24 10:46:43','0:0:0:0:0:0:0:1',1696,'The user medzizi has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1891,'2021-08-24 11:38:30','0:0:0:0:0:0:0:1',1696,'The user medzizi has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1892,'2021-08-24 11:38:34','0:0:0:0:0:0:0:1',1696,'The user medzizi has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1936,'2021-08-25 11:06:00','192.168.3.59',1890,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1937,'2021-08-25 13:30:33','0:0:0:0:0:0:0:1',1696,'The user medzizi has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1938,'2021-08-25 13:30:48','192.168.3.59',1890,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1939,'2021-08-25 13:35:17','192.168.3.59',1890,'The user issam has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1940,'2021-08-25 13:35:24','192.168.3.59',1890,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1944,'2021-08-25 14:10:16','0:0:0:0:0:0:0:1',1696,'The user medzizi has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1945,'2021-08-25 14:12:52','192.168.3.59',1890,'The user issam has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1946,'2021-08-25 14:18:27','192.168.3.59',1890,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(2008,'2021-08-27 13:26:55','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenix. Reason: Bad credentials','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(2009,'2021-08-27 13:27:06','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(2013,'2021-08-27 14:01:47','0:0:0:0:0:0:0:1',2,'The user fenix has logged out','INFO','/security/exception/index','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(2123,'2021-08-30 07:52:24','0:0:0:0:0:0:0:1',1696,'The user medzizi has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(2124,'2021-08-30 07:53:53','192.168.3.59',1890,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(2140,'2021-08-30 08:26:49','192.168.3.59',1890,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(2158,'2021-08-30 11:54:01','192.168.3.59',1890,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(2168,'2021-08-30 13:19:43','0:0:0:0:0:0:0:1',1696,'The user medzizi has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(2169,'2021-08-30 13:21:48','0:0:0:0:0:0:0:1',1696,'The user medzizi has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(2190,'2021-08-30 14:06:09','192.168.3.59',1890,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(2275,'2021-08-31 09:59:56','127.0.0.1',1696,'The user medzizi has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3229,'2021-09-03 14:09:21','0:0:0:0:0:0:0:1',1696,'The user medzizi has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3240,'2021-09-03 14:17:32','0:0:0:0:0:0:0:1',1696,'The user medzizi has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3459,'2021-09-04 09:06:57','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenix. Reason: Bad credentials','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3460,'2021-09-04 09:07:06','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3461,'2021-09-04 09:17:58','127.0.0.1',2,'The user fenix has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3464,'2021-09-04 09:21:13','0:0:0:0:0:0:0:1',3463,'The user admin has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3465,'2021-09-04 09:21:43','0:0:0:0:0:0:0:1',3463,'The user admin has logged out','INFO','/security/index','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3468,'2021-09-04 09:22:23','0:0:0:0:0:0:0:1',3463,'The user admin has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3469,'2021-09-04 09:22:48','0:0:0:0:0:0:0:1',3463,'The user admin has logged out','INFO','/security/index','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3499,'2021-09-06 07:28:39','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenix. Reason: Bad credentials','WARN','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3498,'2021-09-06 07:28:30','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenix. Reason: Bad credentials','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3472,'2021-09-04 09:23:20','0:0:0:0:0:0:0:1',3463,'The user admin has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3500,'2021-09-06 07:28:44','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenixxx. Reason: Bad credentials','WARN','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3501,'2021-09-06 07:28:51','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3831,'2021-09-08 08:40:04','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3835,'2021-09-08 10:10:56','192.168.3.59',1890,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3836,'2021-09-08 10:58:14','127.0.0.1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Linux; Android 8.0; Pixel 2 Build/OPD3.170816.012) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Mobile Safari/537.36'),(3844,'2021-09-08 14:35:31','192.168.3.59',1890,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(4058,'2021-09-10 13:15:36','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36'),(4066,'2021-09-10 14:52:15','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36'),(4067,'2021-09-10 14:52:25','0:0:0:0:0:0:0:1',2,'The user fenix has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36'),(4068,'2021-09-10 14:52:34','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36'),(4073,'2021-09-10 15:27:35','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36'),(4076,'2021-09-10 15:29:37','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36'),(4112,'2021-09-11 09:56:28','192.168.3.59',1890,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(4581,'2021-09-20 10:55:38','192.168.3.59',1890,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36'),(4686,'2021-09-21 10:57:20','192.168.3.59',1890,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36'),(4989,'2021-09-29 07:38:50','192.168.3.59',1890,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36'),(5007,'2021-09-30 14:30:48','192.168.3.59',1890,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36'),(5055,'2021-09-30 15:41:08','192.168.3.59',NULL,'Failed login attempt with username : issam. Reason: Bad credentials','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36'),(5056,'2021-09-30 15:41:16','192.168.3.59',1890,'The user issam has logged in.','INFO','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36'),(5096,'2021-10-01 13:35:27','192.168.3.50',NULL,'Failed login attempt with username : admin. Reason: Bad credentials','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.61 Safari/537.36'),(5134,'2021-10-01 14:24:35','192.168.3.59',1890,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36'),(6073,'2021-11-29 14:48:19','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenix. Reason: Bad credentials','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36'),(6074,'2021-11-29 14:48:24','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenix. Reason: Bad credentials','WARN','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36'),(6075,'2021-11-29 14:48:58','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36'),(6119,'2021-12-08 09:21:46','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : medzizi. Reason: Bad credentials','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36'),(6120,'2021-12-08 09:22:19','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenix. Reason: Bad credentials','WARN','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36'),(6121,'2021-12-08 09:22:28','0:0:0:0:0:0:0:1',1696,'The user medzizi has logged in.','INFO','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36'),(6122,'2021-12-08 11:58:43','0:0:0:0:0:0:0:1',1696,'The user medzizi has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36'),(6152,'2021-12-09 14:59:05','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenix. Reason: Bad credentials','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36'),(6153,'2021-12-09 14:59:10','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36'),(7265,'2022-02-23 08:40:10','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenix. Reason: Bad credentials','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.102 Safari/537.36'),(7266,'2022-02-23 08:40:16','0:0:0:0:0:0:0:1',4075,'The user admin has logged in.','INFO','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.102 Safari/537.36');
/*!40000 ALTER TABLE `sc__activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sc__role`
--

DROP TABLE IF EXISTS `sc__role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sc__role` (
  `id` int NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sc__role`
--

LOCK TABLES `sc__role` WRITE;
/*!40000 ALTER TABLE `sc__role` DISABLE KEYS */;
INSERT INTO `sc__role` VALUES (1,'ROLE_ADMIN',NULL),(2,'ROLE_USER',NULL);
/*!40000 ALTER TABLE `sc__role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sc__routes`
--

DROP TABLE IF EXISTS `sc__routes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sc__routes` (
  `id` int NOT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `route` varchar(255) DEFAULT NULL,
  `order_num` int DEFAULT NULL,
  `parent_id` int DEFAULT NULL,
  `level` int NOT NULL DEFAULT '0',
  `color` varchar(20) NOT NULL DEFAULT '#ff0000',
  `position` varchar(255) DEFAULT NULL,
  `css_class` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn6jx313rxe5eg4y46dw596xpj` (`parent_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sc__routes`
--

LOCK TABLES `sc__routes` WRITE;
/*!40000 ALTER TABLE `sc__routes` DISABLE KEYS */;
INSERT INTO `sc__routes` VALUES (1,'home','dashboard',NULL,'/',1,NULL,0,'#03BF00','1',NULL),(9,'info','about',NULL,'/about',12,NULL,0,'#FFEAAE','1',NULL),(106,'arrow-alt-circle-right','collaborators',NULL,'#',5,10,1,'#ff0000','1',NULL),(107,'arrow-alt-circle-right','depots',NULL,'#',5,10,1,'#ff0000','1',NULL),(108,'arrow-alt-circle-right','Autres',NULL,'#',5,10,1,'#ff0000','1',NULL),(20,'arrow-alt-circle-right','treatments',NULL,'#',10,NULL,0,'#ff0000','1',NULL),(30,'arrow-alt-circle-right','consultations',NULL,'#',11,NULL,0,'#ff0000','1',NULL),(201,'arrow-alt-circle-right','sales_documents',NULL,'#',9,20,1,'#ff0000','1',NULL),(105,'arrow-alt-circle-right','suppliers',NULL,'/supplier?page=1\n',5,10,1,'#cba057','1',NULL),(104,'arrow-alt-circle-right','clients',NULL,'#',5,10,1,'#ff0000','1',NULL),(103,'arrow-alt-circle-right','formulas',NULL,'#',4,10,1,'#ff0000','1',NULL),(101,'boxes','items','product','/product/0?page=1\r\n',3,10,1,'#cba057','1',NULL),(102,'arrow-alt-circle-right','categories\n',NULL,'/product/classification/0\n',3,10,1,'#cba057','1',NULL),(10,'boxes','structure',NULL,'#',2,0,0,'#cba057','1',NULL),(202,'arrow-alt-circle-right','purchase_documents',NULL,'#',9,20,1,'#ff0000','1',NULL),(203,'arrow-alt-circle-right','inventory_documents',NULL,'#',9,20,1,'#ff0000','1',NULL),(204,'arrow-alt-circle-right','internal_documents',NULL,'#',9,20,1,'#ff0000','1',NULL),(205,'arrow-alt-circle-right','production_management',NULL,'#',9,20,1,'#ff0000','1',NULL),(206,'arrow-alt-circle-right','settlement_management',NULL,'#',9,20,1,'#ff0000','1',NULL),(207,'arrow-alt-circle-right','inventory_entry',NULL,'#',9,20,1,'#ff0000','1',NULL),(1012,'plus-square','Ajouter','add','#',21,101,2,'#ccc','1','text-end'),(10121,'plus-square','production.product.finished_products','add_1','/product/add/2',23,1012,3,'#cccccc','2',NULL),(10122,'plus-square','production.product.semi_finished_products','add_1','/product/add/1',22,1012,3,'#cccccc','2',NULL),(10123,'plus-square','production.product.raw_material','add_3','/product/add/3',23,1012,3,'#cccccc','2',NULL);
/*!40000 ALTER TABLE `sc__routes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sc__routes_role`
--

DROP TABLE IF EXISTS `sc__routes_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sc__routes_role` (
  `route_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`route_id`,`role_id`),
  KEY `FK561n5ab6x6v2uc3vu5o372ngg` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sc__routes_role`
--

LOCK TABLES `sc__routes_role` WRITE;
/*!40000 ALTER TABLE `sc__routes_role` DISABLE KEYS */;
INSERT INTO `sc__routes_role` VALUES (1,1),(9,1),(10,1),(20,1),(30,1),(101,1),(102,1),(103,1),(104,1),(105,1),(106,1),(107,1),(108,1),(201,1),(202,1),(203,1),(204,1),(205,1),(206,1),(207,1),(1011,1),(1012,1),(1013,1),(10111,1),(10112,1),(10113,1),(10121,1),(10122,1),(10123,1);
/*!40000 ALTER TABLE `sc__routes_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sc__setting`
--

DROP TABLE IF EXISTS `sc__setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sc__setting` (
  `id` bigint NOT NULL,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK40gjxe1w8bfmxew2y37j9uxkk` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sc__setting`
--

LOCK TABLES `sc__setting` WRITE;
/*!40000 ALTER TABLE `sc__setting` DISABLE KEYS */;
/*!40000 ALTER TABLE `sc__setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sc__user_role`
--

DROP TABLE IF EXISTS `sc__user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sc__user_role` (
  `user_id` bigint NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK4tyblbkivd9nse1aqp0hs7mf2` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sc__user_role`
--

LOCK TABLES `sc__user_role` WRITE;
/*!40000 ALTER TABLE `sc__user_role` DISABLE KEYS */;
INSERT INTO `sc__user_role` VALUES (2,1),(1696,1),(1890,1),(1890,2),(3463,1),(3463,2),(4030,1),(4030,2),(4075,1),(4075,2),(6118,1),(6118,2);
/*!40000 ALTER TABLE `sc__user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sc__users`
--

DROP TABLE IF EXISTS `sc__users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sc__users` (
  `id` bigint NOT NULL,
  `active` bit(1) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `reset_password_token` varchar(255) DEFAULT NULL,
  `user_name` longtext NOT NULL,
  `userpassword` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sc__users`
--

LOCK TABLES `sc__users` WRITE;
/*!40000 ALTER TABLE `sc__users` DISABLE KEYS */;
INSERT INTO `sc__users` VALUES (2,_binary '','2021-05-16 13:28:08','fayssal.note@gmail.com','2021-06-21 08:13:31','$2a$10$1hEDnt/sLkSYeeojtqcmk.k6N8kB2hyWXPmdcrvM4cGslaPYONZj.','yUPton5UmIXbBPbafpESI0HQPrgwr1','fenix','No_pass0'),(1890,_binary '','2021-08-24 11:31:12','issam@gmail.com',NULL,'$2a$12$dYNqs4Yy1MlFNC1qsXi1Ne.8vR2y4lJx8s5ujyF2p4nxpD4C8wIKm',NULL,'issam','123456'),(4075,_binary '','2021-09-10 15:28:51','admin@example.com',NULL,'$2a$12$EtwkE1VjTI1g7Shyt9ZkLO5nQ7AxBE0P65U1HKjYlti6Qs/uCCQx.','456454564','admin','password'),(5015,_binary '\0','2021-09-30 14:33:00','test@test.com',NULL,'$2a$12$3z8vplgym0vlrp4Si/C0Tuzro6YYqHqUNIu45gGKc8i95XKtVnDe2',NULL,'test','123456'),(1696,_binary '','2021-08-24 10:28:11','med.zizi@topchef.ma','2021-08-24 10:29:36','$2a$10$1hEDnt/sLkSYeeojtqcmk.k6N8kB2hyWXPmdcrvM4cGslaPYONZj.',NULL,'medzizi','123456');
/*!40000 ALTER TABLE `sc__users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supl__address`
--

DROP TABLE IF EXISTS `supl__address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supl__address` (
  `supplier_id` bigint NOT NULL,
  `address_id` bigint NOT NULL,
  KEY `FK6tee6gyyne42x1jfw1su570b0` (`address_id`),
  KEY `FKc0tu4qgpwpifhekd41pa90r1` (`supplier_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supl__address`
--

LOCK TABLES `supl__address` WRITE;
/*!40000 ALTER TABLE `supl__address` DISABLE KEYS */;
INSERT INTO `supl__address` VALUES (9,6507),(8,6500),(8,6499),(8,6496),(8,6497),(6521,6523),(12,6533),(9,6506),(9,6502),(9,6508),(6521,6524),(1,6487),(1,6488),(7069,7070),(51,7075),(2,7077);
/*!40000 ALTER TABLE `supl__address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supl__classification`
--

DROP TABLE IF EXISTS `supl__classification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supl__classification` (
  `id` bigint NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supl__classification`
--

LOCK TABLES `supl__classification` WRITE;
/*!40000 ALTER TABLE `supl__classification` DISABLE KEYS */;
INSERT INTO `supl__classification` VALUES (1,_binary '','FEMB','fournisseurs Emballages'),(2,_binary '','FMP','fournisseurs Matière première');
/*!40000 ALTER TABLE `supl__classification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supl__contact`
--

DROP TABLE IF EXISTS `supl__contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supl__contact` (
  `supplier_id` bigint NOT NULL,
  `contact_id` bigint NOT NULL,
  KEY `FKa0ot879ynkvbxol6c8ixpohu` (`contact_id`),
  KEY `FKniuh5g1uq3duc9mq0kkcxvw7p` (`supplier_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supl__contact`
--

LOCK TABLES `supl__contact` WRITE;
/*!40000 ALTER TABLE `supl__contact` DISABLE KEYS */;
INSERT INTO `supl__contact` VALUES (7069,7071),(1,6978),(1,6971),(1,6970),(51,7074),(2,7078);
/*!40000 ALTER TABLE `supl__contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supl__supplier`
--

DROP TABLE IF EXISTS `supl__supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supl__supplier` (
  `id` bigint NOT NULL,
  `social_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `classification_id` bigint NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `ice` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK28nj16x67ey3kf0ym2800q68x` (`classification_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supl__supplier`
--

LOCK TABLES `supl__supplier` WRITE;
/*!40000 ALTER TABLE `supl__supplier` DISABLE KEYS */;
INSERT INTO `supl__supplier` VALUES (1,'Bougrini','S1OASB','MPere@gmail.com',' Fournisseurs Strategiques','0644495470',2,_binary '',NULL,'2022-01-13 12:23:33','1234567890000578888'),(2,'Avicole Slaoui Mehdi','S2OASA','MPere',' Fournisseurs Strateeegiques',NULL,2,_binary '',NULL,'2022-01-12 14:50:30','123456789000057'),(3,'Avicole l\'entente','S3OAEC','MPere',' Fournisseurs Strateeegiques',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(4,'Salhi L\'houssine ','S1LSAL','MPere',' Fournisseurs Strateeegiques',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(5,'Belalia Farid*','S2LBEF','MPere',' Fournisseurs Strateeegiques',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(6,'Belalia Bousselham*','S2LBEB','MPere',' Fournisseurs Strateeegiques',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(7,'Belalia Abdelah ','S3LBEA','MPere',' Fournisseurs Strateeegiques',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(8,'TBI','S01TBI ','MPere',' Fournisseurs Strateeegiques',NULL,2,_binary '',NULL,'2021-12-31 14:56:40','123456789000057'),(9,'Comaner','S02COM','MPere',' Fournisseurs Strateeegiques',NULL,2,_binary '',NULL,'2021-12-31 15:09:55','123456789000057'),(10,'Azelis','S03AZL','MPere',' Fournisseurs Strateeegiques',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(11,'BELVIM','S04BEL','MPere',' Fournisseurs Strateeegiques',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(12,'Colin','S05COL','MPere',' Fournisseurs Strateeegiques',NULL,2,_binary '',NULL,'2022-01-03 14:00:36','123456789000057'),(13,'Arome&Co','S06ARC','MPere',' Fournisseurs Strateeegiques',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(14,'IMCD ','S07IMC','MPere',' Fournisseurs Strateeegiques',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(15,'Lesieur','S08LES','MPere',' Fournisseurs Strateeegiques',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(16,'Espace Avicole','N1OEAA','MPere',' Fournisseurs Nevralgiques ',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(17,'Benaloua Lghazi','N1LBEG','MPere',' Fournisseurs Nevralgiques ',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(18,'FORTRADE','N01FOR','MPere',' Fournisseurs Nevralgiques ',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(19,'MARJAC','N02MAR','MPere',' Fournisseurs Nevralgiques ',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(20,'GS DUNN','N03GSD','MPere',' Fournisseurs Nevralgiques ',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(21,'AGRO MAGGASINO','N04AGM','MPere',' Fournisseurs Nevralgiques ',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(22,'AFE DISTRUBITION','N05AFE','MPere',' Fournisseurs Nevralgiques ',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(23,'Huilerie de Souss','N06HDS','MPere',' Fournisseurs Nevralgiques ',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(24,'Metarom','N07MET','MPere',' Fournisseurs Nevralgiques ',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(25,'Ovodis','C1OEAB','MPere',' Fournisseurs Courants',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(26,'Kraimi Said','C1LKRS','MPere',' Fournisseurs Courants',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(27,'Rziki Abdessalam','C2LRIA','MPere',' Fournisseurs Courants',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(28,'DISPROQUIMA','C01DIQ ','MPere',' Fournisseurs Courants',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(29,'DISPRODIV','C02DIS ','MPere',' Fournisseurs Courants',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(30,'Lacasem','C03LAC','MPere',' Fournisseurs Courants',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(31,'PERLA FOOD','C04PER','MPere',' Fournisseurs Courants',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(32,'CADILHAC','C05CDI','MPere',' Fournisseurs Courants',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(33,'AVM CHIM','C06AVM','MPere',' Fournisseurs Courants',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(34,'Maroc Assaisonnements','C07MAS','zizi.med@gmail.com',' Fournisseurs Courants','',2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(35,'Baltimar','C08BAL','MPere',' Fournisseurs Courants',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(36,'MOLINA','C09MOL','MPere',' Fournisseurs Courants',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(37,'OUBAHA','C10OUB ','MPere',' Fournisseurs Courants',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(38,'Socodil','C11SOC','MPere',' Fournisseurs Courants',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(39,'Ipexa food','C12IPF','MPere',' Fournisseurs Courants',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(40,'Extralait','C13EXT','MPere',' Fournisseurs Courants',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(41,'Divers','C14DVR ','MPere',' Fournisseurs Courants',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(42,'TIANJIN KUNYU','C15TIA','MPere',' Fournisseurs Courants',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(43,'FC2','C16FRC ','MPere',' Fournisseurs Courants',NULL,2,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(44,'NAPC','S01NAP','Emballages',' Fournisseurs Strateeegiques',NULL,1,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(45,'Mikafric','S02MIK ','Emballages',' Fournisseurs Strateeegiques',NULL,1,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(46,'Effeplast','S03EFP','Emballages',' Fournisseurs Strateeegiques',NULL,1,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(47,'Op box','S04OPB ','Emballages',' Fournisseurs Strateeegiques',NULL,1,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(48,'Rovip','S05ROV','Emballages',' Fournisseurs Strateeegiques',NULL,1,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(49,'Extraplast','S06EXT','Emballages',' Fournisseurs Strateeegiques',NULL,1,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(50,'IMCD','S07IMC','Emballages',' Fournisseurs Strateeegiques',NULL,1,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(51,'Wamda','S08WMD','Emballages',' Fournisseurs Strateeegiques',NULL,1,_binary '',NULL,'2022-01-12 13:57:22','123456789000057'),(52,'Unibag maghreb','N01UBM','Emballages',' Fournisseurs Strateeegiques',NULL,1,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(53,'Graph pro','N02GPR','Emballages',' Fournisseurs Nevralgiques ',NULL,1,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(54,'CMCP','N03CMC','Emballages',' Fournisseurs Nevralgiques ',NULL,1,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(55,'GPC','N04GPC ','Emballages',' Fournisseurs Nevralgiques ',NULL,1,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(56,'Dima plast','C01DPL','Emballages',' Fournisseurs Nevralgiques ',NULL,1,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(57,'Disprodiv','C02DIS ','Emballages',' Fournisseurs Courants',NULL,1,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(58,'Ifriquia plastique','C03IFP','Emballages',' Fournisseurs Courants',NULL,1,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(59,'Acraplast','C04ACP','Emballages',' Fournisseurs Courants',NULL,1,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(60,'New smart company','C05NSC','Emballages',' Fournisseurs Courants',NULL,1,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(61,'Moro pack','C06MOP','Emballages',' Fournisseurs Courants',NULL,1,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(62,'Smn Adhesives','C07SMA','Emballages',' Fournisseurs Courants',NULL,1,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(63,'Mogesse','C08MOG','Emballages',' Fournisseurs Courants',NULL,1,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(64,'Sonatec','C09SON ','Emballages',' Fournisseurs Courants',NULL,1,_binary '','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(6459,'gggggg','88890','admin@example.com',NULL,NULL,1,_binary '\0','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(6460,'dddd','88890555','admin@example.com','je suis la','0644495470',1,_binary '\0','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(6463,'gggggg _hello','88890','admin@example.com','je suis la test 2','0644495470',2,_binary '\0','2021-12-01 14:48:43','2021-12-30 14:49:02','123456789000057'),(6464,'gggggg','88890','admin@example.com','je suis la test 2','0644495470',2,_binary '',NULL,'2021-12-29 15:30:42','123456789000057'),(6465,'gggggg','88890','admin@example.com','je suis la','0644495470',2,_binary '',NULL,'2021-12-29 15:49:42','123456789000057'),(6521,'Fenix TEST','FNTS','admin0@example.com','je suis la02','+212644495470',1,_binary '',NULL,'2022-01-03 08:22:04','123456789000057'),(7069,'gggggg','88890','Fayssal.zf@gmail.com','je suis la','0644495470',1,_binary '',NULL,'2022-01-12 13:41:25','1234567890000578888'),(7072,'89797979878989','88890555','admin@example.com','je suis la','064449000000',1,_binary '','2022-01-12 13:42:14','2022-01-12 13:42:14','1234567890000578888');
/*!40000 ALTER TABLE `supl__supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vnd__address`
--

DROP TABLE IF EXISTS `vnd__address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vnd__address` (
  `id` bigint NOT NULL,
  `active` bit(1) NOT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `line_one` varchar(255) NOT NULL,
  `line_tow` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `zip_code` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vnd__address`
--

LOCK TABLES `vnd__address` WRITE;
/*!40000 ALTER TABLE `vnd__address` DISABLE KEYS */;
INSERT INTO `vnd__address` VALUES (4378,_binary '','fes0188','Morocco01888','line One0188','line tow adress0188','address facturetion01888',555550188),(4382,_binary '','fes','Morocco','line One','line tow adress','address facturetion',55555),(4386,_binary '','fes','Morocco','line One','line tow adress','address facturetion',55555),(4402,_binary '','fes','Morocco','line One','line tow adress','address facturetion',55555),(4422,_binary '','aa','a','a','','a',0),(4426,_binary '','1111','111','111','','111',0),(4487,_binary '','aa','aa','a','','a',0),(4509,_binary '','fes','Morocco','line One','linecc  cc cc','address facturetion',55555),(4513,_binary '','fes','Morocco','line One','line tow adress','address facturetion',55555),(4519,_binary '','fes','Morocco','line One','line tow adress','address facturetion',55555),(4530,_binary '','test ville','test pays','test adr1','','test nom adr',0),(4534,_binary '','test ville adr','test pays adre','test adrea1','','test nom adre',0),(4538,_binary '','casablanca','aa','ss','','20 rue taza',0),(4542,_binary '','casablanca','zz','ss','','20 rue taza',0),(4547,_binary '','dd00','dd00','dd00','dd00','dd00',7777777),(4551,_binary '','casablanca','111','ss','ss','20 rue taza',25555),(4558,_binary '','fes','Morocco','line One','line tow adress','address facturetion',55555),(4562,_binary '','test ville','test pays','test adr1','test adr2','test nom adre',5555),(4566,_binary '','ville','pays','adr1','','nom adr',0),(4598,_binary '','hh','hh','ff','ff','fff',444),(4602,_binary '','a','a','a','a','a',123),(4606,_binary '','bb','bb','bb','bb','bb',123),(4781,_binary '','22222222','22222222','22222222','22222222','22222222',22222222),(5017,_binary '','111','1111','111','111','1111',1111),(5021,_binary '','sgg','sggs','sgs','sgg','sgfgsg',0),(5025,_binary '','46','5456','4564564654','','5464564',465),(5029,_binary '','dsf','dsf','ds','','dsfq',6545456),(5427,_binary '','casablanca','maroc','casablnca','','facturation',0),(5437,_binary '','mycity','mycountry','line one','line two','my name',2000),(5441,_binary '','000','000','0000','000','00000',555),(5445,_binary '','007','007','007','007','007',78),(5449,_binary '','007','007','007','007','007',7),(5453,_binary '','008','008','008','008','0080',7877),(5457,_binary '','777','777','777','777','777',7777),(5466,_binary '','777','777','777','777','7777',77),(5473,_binary '','98989','98989','98989','98989','98989',98989),(5477,_binary '','98989','98989','98989','98989','98989',98989),(5481,_binary '','98989','98989','98989','98989','98989',98989),(5485,_binary '','98989','98989','98989','98989','98989',98989),(5489,_binary '','98989','98989','98989','98989','98989',98989),(5493,_binary '','98989','98989','98989','98989','98989',98989),(5497,_binary '','98989','98989','98989','98989','98989',98989),(5501,_binary '','98989','98989','98989','98989','98989',98989),(5505,_binary '','98989','98989','98989','98989','98989',98989),(5509,_binary '','98989','98989','98989','98989','98989',98989),(5513,_binary '','98989','98989','98989','98989','98989',98989),(5517,_binary '','98989','98989','98989','98989','98989',98989),(5521,_binary '','98989','98989','98989','98989','98989',98989),(5525,_binary '','98989','98989','98989','98989','98989',98989),(5529,_binary '','98989','98989','98989','98989','98989',98989),(5533,_binary '','98989','98989','98989','98989','98989',98989),(5537,_binary '','98989','98989','98989','98989','98989',98989),(5541,_binary '','98989','98989','98989','98989','98989',98989),(5545,_binary '','98989','98989','98989','98989','98989',98989),(5549,_binary '','87878','8787','8787','8787','8787',87878),(5553,_binary '','87878','8787','8787','8787','8787',87878),(5557,_binary '','87878','8787','8787','8787','8787',87878),(5561,_binary '','87878','8787','8787','8787','8787',87878),(5565,_binary '','87878','8787','8787','8787','8787',87878),(5569,_binary '','87878','8787','8787','8787','8787',87878),(5573,_binary '','87878','8787','8787','8787','8787',87878),(5577,_binary '','87878','8787','8787','8787','8787',87878),(5581,_binary '','87878','8787','8787','8787','8787',87878),(5585,_binary '','87878','8787','8787','8787','8787',87878),(5589,_binary '','87878','8787','8787','8787','8787',87878),(5593,_binary '','87878','8787','8787','8787','8787',87878),(5597,_binary '','87878','8787','8787','8787','8787',87878),(5601,_binary '','87878','8787','8787','8787','8787',87878),(5605,_binary '','87878','8787','8787','8787','8787',87878),(5609,_binary '','87878','8787','8787','8787','8787',87878),(5613,_binary '','87878','8787','8787','8787','8787',87878),(5617,_binary '','87878','8787','8787','8787','8787',87878),(5621,_binary '','87878','8787','8787','8787','8787',87878),(5625,_binary '','87878','8787','8787','8787','8787',87878),(5629,_binary '','87878','8787','8787','8787','8787',87878),(5633,_binary '','87878','8787','8787','8787','8787',87878),(5637,_binary '','87878','8787','8787','8787','8787',87878),(5641,_binary '','87878','8787','8787','8787','8787',87878),(5645,_binary '','87878','8787','8787','8787','8787',87878),(5649,_binary '','87878','8787','8787','8787','8787',87878),(5653,_binary '','87878','8787','8787','8787','8787',87878),(5657,_binary '','87878','8787','8787','8787','8787',87878),(5661,_binary '','87878','8787','8787','8787','8787',87878),(5665,_binary '','87878','8787','8787','8787','8787',87878),(5669,_binary '','888','88','888','888','888',888),(5677,_binary '','mycity','mycountry','line one','line two','my name',2000),(5682,_binary '','mycity','mycountry','line one','line two','my name',2000),(5686,_binary '','mycity','mycountry','line one','line two','my name',2000),(5700,_binary '','56456','456','654','654','654',654),(5710,_binary '','mycity','mycountry','line one','line two','my name',2000),(5714,_binary '','mycity','mycountry','line one','line two','my name',2000),(5725,_binary '','mycity','mycountry','line one','line two','my name',2000),(5928,_binary '','fes0188','Morocco01888','line One0188','line tow adress0188','address facturetion01888',555550188),(5930,_binary '','fes0188-edited','Morocco-edited','line One-edited','line tow adress-edited','address facturetion-edited',9999),(5932,_binary '','fes0188-edited','Morocco-edited','line One-edited','line tow adress-edited','address facturetion-edited',9999),(5934,_binary '','fes0188-edited','Morocco-edited','line One-edited','line tow adress-edited','address facturetion-edited',9999),(5936,_binary '','fes','Morocco','line One','linecc  cc cc','address facturetion',55555),(5938,_binary '','fes0188-edited','Morocco-edited','line One-edited','line tow adress-edited','address facturetion-edited',9999),(5940,_binary '','fes0188-edited','Morocco-edited','line One-edited','line tow adress-edited','address facturetion-edited',9999),(5942,_binary '','fes0188-edited','Morocco-edited','line One-edited','line tow adress-edited','address facturetion-edited',9999),(5944,_binary '','111','1111','111','111','1111',1111),(5946,_binary '','111','1111','111','111','1111',1111),(5950,_binary '','22222222-edit','22222222-edit','22222222-edit','22222222-edit','22222222-edit',9999),(5952,_binary '','111','1111','111','111','1111',1111),(5954,_binary '','casablanca','111','ss','ss','20 rue taza',25555),(5956,_binary '','casablanca','111','ss','ss','20 rue taza',25555),(5958,_binary '','111','1111','111','111','1111',1111),(5962,_binary '','casablanca','111','ss','ss','20 rue taza',25555),(5974,_binary '','fes0188-edited','Morocco-edited','line One-edited','line tow adress-edited','address facturetion-edited',9999),(5985,_binary '','fes','Morocco','line One','line tow adress','address facturetion',55555),(5987,_binary '','fes0188-edited','Morocco-edited','line One-edited','line tow adress-edited','address facturetion-edited',9999),(5989,_binary '','fes','Morocco','line One','line tow adress','address facturetion',55555),(5991,_binary '','fes','Morocco','line One','line tow adress','address facturetion',55555),(6008,_binary '','fes0188-edited','Morocco-edited','line One-edited','line tow adress-edited','address facturetion-edited',9999),(6010,_binary '','fes0188-edited','Morocco-edited','line One-edited','line tow adress-edited','address facturetion-edited',9999),(6012,_binary '','fes0188-edited','Morocco-edited','line One-edited','line tow adress-edited','address facturetion-edited',9999),(6039,_binary '','dd00','dd00','dd00','dd00','dd00',7777777);
/*!40000 ALTER TABLE `vnd__address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vnd__classification`
--

DROP TABLE IF EXISTS `vnd__classification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vnd__classification` (
  `id` bigint NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vnd__classification`
--

LOCK TABLES `vnd__classification` WRITE;
/*!40000 ALTER TABLE `vnd__classification` DISABLE KEYS */;
INSERT INTO `vnd__classification` VALUES (1,_binary '','FMP','5645645'),(2,_binary '','FEM','Fournisseur emballage '),(5582,_binary '\0','454574','4545'),(5578,_binary '\0','454574','4545'),(5574,_binary '\0','65','685'),(5570,_binary '\0','454574','4545'),(5566,_binary '\0','454574','4545'),(5562,_binary '\0','454574','4545'),(5558,_binary '\0','454574','4545'),(5554,_binary '\0','454574','4545'),(5550,_binary '\0','454574','4545'),(5546,_binary '\0','989898','9898989'),(5542,_binary '\0','989898','9898989'),(5538,_binary '\0','989898','9898989'),(5534,_binary '\0','989898','9898989'),(5530,_binary '\0','989898','9898989'),(5526,_binary '\0','989898','9898989'),(5522,_binary '\0','989898','9898989'),(5518,_binary '\0','989898','9898989'),(5514,_binary '\0','989898','9898989'),(5510,_binary '\0','989898','9898989'),(5506,_binary '\0','989898','9898989'),(5502,_binary '\0','989898','9898989'),(5498,_binary '\0','989898','9898989'),(5494,_binary '\0','989898','9898989'),(5490,_binary '\0','989898','9898989'),(5486,_binary '\0','989898','9898989'),(5482,_binary '\0','989898','9898989'),(5478,_binary '\0','989898','9898989'),(5474,_binary '\0','989898','9898989'),(5467,_binary '\0','777','7777'),(5458,_binary '\0','777','777'),(5454,_binary '\0','85454','008'),(5450,_binary '\0','007','007'),(5446,_binary '\0','007','007'),(5442,_binary '\0','000','000'),(5438,_binary '\0','code mc','name mc'),(5428,_binary '\0','0124','test'),(5032,_binary '\0','qsdqsd','qsdqs'),(5030,_binary '\0','sdf','df'),(5026,_binary '\0','sgs','sdqfqs'),(5022,_binary '\0','64556465','566456'),(4782,_binary '\0','22222222','22222222'),(5018,_binary '\0','',''),(4607,_binary '\0','bb','bb'),(4603,_binary '\0','a','a'),(4599,_binary '\0','hhh','hghh'),(4567,_binary '\0','code classi','nom classi'),(4563,_binary '\0','test code classi','test nom classi'),(4559,_binary '\0','code','New one'),(4552,_binary '\0','',''),(4548,_binary '\0','dd00','dd00'),(4543,_binary '\0','aa','ISsam elyazri'),(4539,_binary '\0','aaa','aaa'),(4535,_binary '\0','test code class','test nom class'),(4531,_binary '\0','test code classif','test nom classif'),(4522,_binary '\0','944','Name 667'),(4520,_binary '\0','code','New oneUpdated'),(4517,_binary '\0','545','Name 238'),(4514,_binary '\0','',''),(4510,_binary '\0','code','New one'),(4488,_binary '\0','a','a'),(4438,_binary '\0','test-type-edit','test-type-edit'),(4436,_binary '\0','test-add-edit','test-add-edit'),(4427,_binary '\0','11','111'),(4423,_binary '\0','a','a'),(4416,_binary '\0','test','tst'),(4414,_binary '\0','444 tyest','test'),(4415,_binary '\0','test10','test10'),(4413,_binary '\0','45770','test0'),(4403,_binary '\0','code','New one'),(4399,_binary '\0','a','a'),(4395,_binary '\0','test1','test1'),(4387,_binary '\0','code','New one'),(4379,_binary '\0','',''),(4383,_binary '\0','code','New one'),(5586,_binary '\0','454574','4545'),(5590,_binary '\0','454574','4545'),(5594,_binary '\0','454574','4545'),(5598,_binary '\0','454574','4545'),(5602,_binary '\0','454574','4545'),(5606,_binary '\0','454574','4545'),(5610,_binary '\0','454574','4545'),(5614,_binary '\0','454574','4545'),(5618,_binary '\0','454574','4545'),(5622,_binary '\0','454574','4545'),(5626,_binary '\0','454574','4545'),(5630,_binary '\0','454574','4545'),(5634,_binary '\0','454574','4545'),(5638,_binary '\0','454574','4545'),(5642,_binary '\0','454574','4545'),(5646,_binary '\0','454574','4545'),(5650,_binary '\0','454574','4545'),(5654,_binary '\0','454574','4545'),(5658,_binary '\0','454574','4545'),(5662,_binary '\0','454574','4545'),(5666,_binary '\0','454574','4545'),(5670,_binary '\0','888','888'),(5683,_binary '\0','code mc','name mc'),(5692,_binary '\0','TDghjs4556','1'),(5693,_binary '\0','545646','564564'),(5694,_binary '\0','',''),(5695,_binary '\0','',''),(5696,_binary '\0','0003','centrales d’achat'),(5697,_binary '\0','',''),(5698,_binary '\0','',''),(5701,_binary '\0','5696','5701'),(5711,_binary '\0','code mc','name mc'),(5715,_binary '','code mc','name mc'),(5726,_binary '','code mc','name mc'),(5737,_binary '\0','a','a');
/*!40000 ALTER TABLE `vnd__classification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vnd__contact`
--

DROP TABLE IF EXISTS `vnd__contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vnd__contact` (
  `id` bigint NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `job` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vnd__contact`
--

LOCK TABLES `vnd__contact` WRITE;
/*!40000 ALTER TABLE `vnd__contact` DISABLE KEYS */;
INSERT INTO `vnd__contact` VALUES (4380,_binary '','fayssal@gmail.com0188','admin0188','fayssal benmoussa0188',' texte note0188','06444954700188'),(4384,_binary '','fayssal@gmail.com','admin','fayssal benmoussa',' texte note','0644495470'),(4388,_binary '','fayssal@gmail.com','admin','fayssal benmoussa',' texte note','0644495470'),(4404,_binary '','fayssal@gmail.com','admin','fayssal benmoussa',' texte note','0644495470'),(4424,_binary '','a','a','a','a','a'),(4428,_binary '','1111','11','111','111','11'),(4489,_binary '','a','a','a','a','111'),(4511,_binary '','fayssal@gmail.com','admin','fayssal benmoussa',' texte note','0644495470'),(4515,_binary '','fayssal@gmail.com','admin','fayssal benmoussa',' texte note','0644495470'),(4521,_binary '','fayssal@gmail.com','admin','fayssal benmoussa',' texte note','0644495470'),(4532,_binary '','test mail cp','test poste cp','test nom cp','test note cp','222222'),(4536,_binary '','testmail@.com','test poste cp','test nom cp','test note','33333'),(4540,_binary '','issyaz2021@gmail.com','aa','ISsam elyazri','aa','7777'),(4544,_binary '','issyaz2021@gmail.com','aa','ISsam elyazri','aa','1114'),(4549,_binary '','issyaz2021@gmail.com','dd00','dd00','ddd','888'),(4553,_binary '','issyaz2021@gmail.com','sss','ISsam elyazri','sss','111'),(4560,_binary '','fayssal@gmail.com','admin','fayssal benmoussa',' texte note','0644495470'),(4564,_binary '','testmailCP@cc.com','test poste cp','test nom cp','test note','22222'),(4568,_binary '','mailcp@mail.com','poste cp','nom cp','notecp','222'),(4600,_binary '','ghshgs@ss.com','hhh','hhh','hshs','77777'),(4604,_binary '','a','a','a','a','987'),(4608,_binary '','ss@ss.com','bb','b','b','1234546'),(4783,_binary '','22222222@gmail.com','22222222','22222222','22222222','22222222'),(5019,_binary '','111@ss.com','111','1111','1111','11'),(5023,_binary '','65464@gma.cpm','45656456','4556456','456','4565'),(5027,_binary '','dsd@gmail.com','qfsd','56465456','sdf','4654654'),(5031,_binary '','fflmklmfk@gma.com','sdf','dsf','sd','4546'),(5429,_binary '','1456@t.cl','2112','car','121','12122'),(5439,_binary '','didid@jjd.com','main job','main contname','it\'s the note','855588'),(5443,_binary '','ddd@d.com','kkd','ssmm','jdjd00','59874'),(5447,_binary '','dsss@kk.com','007','007','007','0858'),(5451,_binary '','ddd@dd.com','007','007','dddd','7885'),(5455,_binary '','dd@dd.com','008','008','851','555'),(5459,_binary '','77@ddsd.com','77','777','7777','7777'),(5468,_binary '','77d@k.com','777','777','4444','777'),(5475,_binary '','9898@ss.com','98989','98989','4sd','9898'),(5479,_binary '','9898@ss.com','98989','98989','4sd','9898'),(5483,_binary '','9898@ss.com','98989','98989','4sd','9898'),(5487,_binary '','9898@ss.com','98989','98989','4sd','9898'),(5491,_binary '','9898@ss.com','98989','98989','4sd','9898'),(5495,_binary '','9898@ss.com','98989','98989','4sd','9898'),(5499,_binary '','9898@ss.com','98989','98989','4sd','9898'),(5503,_binary '','9898@ss.com','98989','98989','4sd','9898'),(5507,_binary '','9898@ss.com','98989','98989','4sd','9898'),(5511,_binary '','9898@ss.com','98989','98989','4sd','9898'),(5515,_binary '','9898@ss.com','98989','98989','4sd','9898'),(5519,_binary '','9898@ss.com','98989','98989','4sd','9898'),(5523,_binary '','9898@ss.com','98989','98989','4sd','9898'),(5527,_binary '','9898@ss.com','98989','98989','4sd','9898'),(5531,_binary '','9898@ss.com','98989','98989','4sd','9898'),(5535,_binary '','9898@ss.com','98989','98989','4sd','9898'),(5539,_binary '','9898@ss.com','98989','98989','4sd','9898'),(5543,_binary '','9898@ss.com','98989','98989','4sd','9898'),(5547,_binary '','9898@ss.com','98989','98989','4sd','9898'),(5551,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5555,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5559,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5563,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5567,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5571,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5575,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5579,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5583,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5587,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5591,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5595,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5599,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5603,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5607,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5611,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5615,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5619,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5623,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5627,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5631,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5635,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5639,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5643,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5647,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5651,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5655,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5659,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5663,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5667,_binary '','5454@d.com','54545','54545','kdkhjnd','545454'),(5671,_binary '','888@dsd.com','888','888','8888d','888'),(5684,_binary '','didid@jjd.com','main job','main contname','it\'s the note','855588'),(5702,_binary '','test@gmail.com','qs','465','qs','4564'),(5712,_binary '','didid@jjd.com','main job','main contname','it\'s the note','855588'),(5716,_binary '','didid@jjd.com','main job','main contname','it\'s the note','855588'),(5727,_binary '','didid@jjd.com','main job','main contname','it\'s the note','855588'),(5929,_binary '','ddfd@dd.com','admin0188','fayssal benmoussa0188',' texte note0188','7877'),(5931,_binary '','edited@edit.com','admin-edited','fayssal benmoussa-edited',' texte note-edited','99999'),(5933,_binary '','email@gmail.com0188','admin-edited','fayssal benmoussa-edited',' texte note-edited','999'),(5935,_binary '','email@gmail.com0188','admin-edited','fayssal benmoussa-edited',' texte note-edited','999'),(5937,_binary '','fayssal@gmail.com','admin','fayssal benmoussa',' texte note','0644495470'),(5939,_binary '','email@gmail.com0188','admin-edited','fayssal benmoussa-edited',' texte note-edited','999'),(5941,_binary '','email@gmail.com0188','admin-edited','fayssal benmoussa-edited',' texte note-edited','999'),(5943,_binary '','email@gmail.com0188','admin-edited','fayssal benmoussa-edited',' texte note-edited','999'),(5945,_binary '','111@ss.com','111','1111','1111','11'),(5947,_binary '','111@ss.com','111','1111','1111','11'),(5951,_binary '','-edit@gmail.com','-edit','-edit','-edit','999999'),(5953,_binary '','111@ss.com','111','1111','1111','11'),(5955,_binary '','issyaz2021@gmail.com','sss','ISsam elyazri','sss','111'),(5957,_binary '','issyaz2021@gmail.com','sss','ISsam elyazri','sss','111'),(5959,_binary '','111@ss.com','111','1111','1111','11'),(5963,_binary '','issyaz2021@gmail.com','sss','ISsam elyazri','sss','111'),(5975,_binary '','email@gmail.com0188','admin-edited','fayssal benmoussa-edited',' texte note-edited','999'),(5986,_binary '','fayssal@gmail.com','admin','fayssal benmoussa',' texte note','0644495470'),(5988,_binary '','email@gmail.com0188','admin-edited','fayssal benmoussa-edited',' texte note-edited','999'),(5990,_binary '','fayssal@gmail.com','admin','fayssal benmoussa',' texte note','0644495470'),(5992,_binary '','fayssal@gmail.com','admin','fayssal benmoussa',' texte note','0644495470'),(6009,_binary '','email@gmail.com0188','admin-edited','fayssal benmoussa-edited',' texte note-edited','999'),(6011,_binary '','email@gmail.com0188','admin-edited','fayssal benmoussa-edited',' texte note-edited','999'),(6013,_binary '','email@gmail.com0188','admin-edited','fayssal benmoussa-edited',' texte note-edited','999'),(6040,_binary '','issyaz2021@gmail.com','dd00','dd00','ddd','888');
/*!40000 ALTER TABLE `vnd__contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vnd__vendor`
--

DROP TABLE IF EXISTS `vnd__vendor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vnd__vendor` (
  `id` bigint NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `code` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `social_reason` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `address_id` bigint NOT NULL,
  `classification_id` int NOT NULL,
  `main_contact_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhn9wtjxeket036wuuqm7ptyat` (`address_id`),
  KEY `FK24a9us2fq3clfot65sc2pct7r` (`classification_id`),
  KEY `FKguvxf76l4l190mvccrael44hw` (`main_contact_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vnd__vendor`
--

LOCK TABLES `vnd__vendor` WRITE;
/*!40000 ALTER TABLE `vnd__vendor` DISABLE KEYS */;
INSERT INTO `vnd__vendor` VALUES (4377,_binary '','a-MC Carton-edited','email@gmail.com-edited','Text Note-edited','Social Reason-edited','999999',6012,5695,6013),(4381,_binary '','aa-MC Carton','email@gmail.com','Text Note','Social Reason 463','0644495470',4382,5695,4384),(4385,_binary '','bb-MC Carton','email@gmail.com','Text Note','Social Reason 276','0644495470',4386,4379,4388),(4401,_binary '\0','MC Carton','email@gmail.com','Text Note','Social Reason 205','0644495470',5991,5695,5992),(4421,_binary '\0','ttest','email@gmail.com','Text Note','test ddd','0644495470',5936,5698,5937),(4508,_binary '','MC Carton','email@gmail.com','Text Note','Social Reason 451 Updated Updated Updated','0644495470',4509,5698,4511),(4512,_binary '','MC Carton','email@gmail.com','Text Note','Social Reason 268 Updated','0644495470',5985,4514,5986),(4518,_binary '','MC Carton','email@gmail.com','Text Note','Social Reason 816','0644495470',4519,4520,4521),(4605,_binary '\0','bb','sss@s.com','bb','bb Updated','1111',4606,2,4608),(4601,_binary '\0','a','a','a','a','123',4602,4603,4604),(4597,_binary '\0','code','gss@s.com','fff','rs','444444',4598,4599,4600),(4546,_binary '','dd0001','dd00@gmailss.com','dd00','dd000','777',6039,2,6040),(4550,_binary '\0','zzz','issyaz2021@gmail.com','zz','zz','444',5962,5694,5963),(4557,_binary '\0','MC Carton','email@gmail.com','Text Note','Social Reason 268','0644495470',4558,4559,4560),(4561,_binary '\0','test code','testmail@mail.com','test note','test rais soc','111111',4562,4563,4564),(4565,_binary '\0','code','mailadrs@mail.com','noter','rois soc','111',4566,4567,4568),(4780,_binary '\0','22222222edit','edit@gmail.com','22222222-edit','22222222-edit','9999',5950,5697,5951),(5016,_binary '\0','111','111@11.com','11','1111','11111',5958,5018,5959),(5020,_binary '\0','dfsgf','sggg@gm.vm','sfgs','sgf','564564654',5021,4379,5023),(5024,_binary '\0','dfssdfsdf','564564564@hmail.com','dfsdf','fdqsdfsdf','464564',5025,5026,5027),(5028,_binary '\0','fqsd','fflmklmfk@gma.com','dqsf','qfdsd','5465456456',5029,5030,5031),(5426,_binary '\0','00123','canedlia@topchef.ma','rien','Canelia','21251614214',5427,5428,5429),(5436,_binary '\0','eeee','ksk@z.com','mc note','sarais','3555',5437,5438,5439),(5440,_binary '\0','0000','es@s.com','0000','0000','05552',5441,5442,5443),(5444,_binary '\0','007','sd@d.com','007','007','7777',5445,5446,5447),(5448,_binary '\0','007','dd@d.com','007','007','777',5449,5450,5451),(5452,_binary '\0','008','sss@s.com','00','008','8774',5453,5454,5455),(5456,_binary '\0','788','777d@dd.com','778','778','7777',5457,5458,5459),(5465,_binary '\0','777','777@s.com','777','777','777',5466,5467,5468),(5472,_binary '\0','9898','9898@s.com','98989','98989','99898',5473,5474,5475),(5476,_binary '\0','9898','9898@s.com','98989','98989','99898',5477,5478,5479),(5480,_binary '\0','9898','9898@s.com','98989','98989','99898',5481,5482,5483),(5484,_binary '\0','9898','9898@s.com','98989','98989','99898',5485,5486,5487),(5488,_binary '\0','9898','9898@s.com','98989','98989','99898',5489,5490,5491),(5492,_binary '\0','9898','9898@s.com','98989','98989','99898',5493,5494,5495),(5496,_binary '\0','9898','9898@s.com','98989','98989','99898',5497,5498,5499),(5500,_binary '\0','9898','9898@s.com','98989','98989','99898',5501,5502,5503),(5504,_binary '\0','9898','9898@s.com','98989','98989','99898',5505,5506,5507),(5508,_binary '\0','9898','9898@s.com','98989','98989','99898',5509,5510,5511),(5512,_binary '\0','9898','9898@s.com','98989','98989','99898',5513,5514,5515),(5516,_binary '\0','9898','9898@s.com','98989','98989','99898',5517,5518,5519),(5520,_binary '\0','9898','9898@s.com','98989','98989','99898',5521,5522,5523),(5524,_binary '\0','9898','9898@s.com','98989','98989','99898',5525,5526,5527),(5528,_binary '\0','9898','9898@s.com','98989','98989','99898',5529,5530,5531),(5532,_binary '\0','9898','9898@s.com','98989','98989','99898',5533,5534,5535),(5536,_binary '\0','9898','9898@s.com','98989','98989','99898',5537,5538,5539),(5540,_binary '\0','9898','9898@s.com','98989','98989','99898',5541,5542,5543),(5544,_binary '\0','9898','9898@s.com','98989','98989','99898',5545,5546,5547),(5548,_binary '\0','897789','8787@dd.com','8787','8787','87878',5549,5550,5551),(5552,_binary '\0','897789','8787@dd.com','8787','8787','87878',5553,5554,5555),(5556,_binary '\0','897789','8787@dd.com','8787','8787','87878',5557,5558,5559),(5560,_binary '\0','897789','8787@dd.com','8787','8787','87878',5561,5562,5563),(5564,_binary '\0','897789','8787@dd.com','8787','8787','87878',5565,5566,5567),(5568,_binary '\0','897789','8787@dd.com','8787','8787','87878',5569,5570,5571),(5572,_binary '\0','897789','8787@dd.com','8787','8787','87878',5573,5574,5575),(5576,_binary '\0','897789','8787@dd.com','8787','8787','87878',5577,5578,5579),(5580,_binary '\0','897789','8787@dd.com','8787','8787','87878',5581,5582,5583),(5584,_binary '\0','897789','8787@dd.com','8787','8787','87878',5585,5586,5587),(5588,_binary '\0','897789','8787@dd.com','8787','8787','87878',5589,5590,5591),(5592,_binary '\0','897789','8787@dd.com','8787','8787','87878',5593,5594,5595),(5596,_binary '\0','897789','8787@dd.com','8787','8787','87878',5597,5598,5599),(5600,_binary '\0','897789','8787@dd.com','8787','8787','87878',5601,5602,5603),(5604,_binary '\0','897789','8787@dd.com','8787','8787','87878',5605,5606,5607),(5608,_binary '\0','897789','8787@dd.com','8787','8787','87878',5609,5610,5611),(5612,_binary '\0','897789','8787@dd.com','8787','8787','87878',5613,5614,5615),(5616,_binary '\0','897789','8787@dd.com','8787','8787','87878',5617,5618,5619),(5620,_binary '\0','897789','8787@dd.com','8787','8787','87878',5621,5622,5623),(5624,_binary '\0','897789','8787@dd.com','8787','8787','87878',5625,5626,5627),(5628,_binary '\0','897789','8787@dd.com','8787','8787','87878',5629,5630,5631),(5632,_binary '\0','897789','8787@dd.com','8787','8787','87878',5633,5634,5635),(5636,_binary '\0','897789','8787@dd.com','8787','8787','87878',5637,5638,5639),(5640,_binary '\0','897789','8787@dd.com','8787','8787','87878',5641,5642,5643),(5644,_binary '\0','897789','8787@dd.com','8787','8787','87878',5645,5646,5647),(5648,_binary '\0','897789','8787@dd.com','8787','8787','87878',5649,5650,5651),(5652,_binary '\0','897789','8787@dd.com','8787','8787','87878',5653,5654,5655),(5656,_binary '\0','897789','8787@dd.com','8787','8787','87878',5657,5658,5659),(5660,_binary '\0','897789','8787@dd.com','8787','8787','87878',5661,5662,5663),(5664,_binary '\0','897789','8787@dd.com','8787','8787','87878',5665,5666,5667),(5668,_binary '\0','888','888@dd.com','888','88','888',5669,5670,5671),(5681,_binary '\0','eeee','ksk@z.com','mc note','sarais','3555',5682,5683,5684),(5699,_binary '\0','56465','test@gmail.com','654','4654','456',5700,5701,5702),(5709,_binary '','eeee','ksk@z.com','mc note','sarais','3555',5710,5711,5712),(5713,_binary '','eeee','ksk@z.com','mc note','sarais','3555',5714,5715,5716),(5724,_binary '','eeee','ksk@z.com','mc note','sarais','3555',5725,5726,5727);
/*!40000 ALTER TABLE `vnd__vendor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-24  2:59:59
