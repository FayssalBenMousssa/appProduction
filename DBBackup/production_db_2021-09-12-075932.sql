-- MySQL dump 10.13  Distrib 8.0.26, for Linux (x86_64)
--
-- Host: localhost    Database: production_db
-- ------------------------------------------------------
-- Server version	8.0.26

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
INSERT INTO `app__exception` VALUES (4077,'Internal Server Error',NULL,'io.jsonwebtoken.ExpiredJwtException','/api/product/index','500','2021-09-11 07:57:49','io.jsonwebtoken.ExpiredJwtException: JWT expired at 2021-09-10T21:36:54Z. Current time: 2021-09-11T07:57:49Z, a difference of 37255119 milliseconds.  Allowed clock skew: 0 milliseconds.\r\n	at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:448)\r\n	at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:550)\r\n	at io.jsonwebtoken.impl.DefaultJwtParser.parseClaimsJws(DefaultJwtParser.java:610)\r\n	at dev.fenix.application.api.util.JwtUtil.extractAllClaims(JwtUtil.java:36)\r\n	at dev.fenix.application.api.util.JwtUtil.extractClaim(JwtUtil.java:30)\r\n	at dev.fenix.application.api.util.JwtUtil.extractUsername(JwtUtil.java:22)\r\n	at dev.fenix.application.api.filters.JwtRequestFilter.doFilterInternal(JwtRequestFilter.java:37)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:103)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:89)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:91)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doHeadersAfter(HeaderWriterFilter.java:90)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:75)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:110)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:80)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:55)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:211)\r\n	at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:183)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:358)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:271)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:202)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:542)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:143)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:374)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)\r\n	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:888)\r\n	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1597)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1130)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.base/java.lang.Thread.run(Thread.java:832)\r\n',NULL,'JWT expired at 2021-09-10T21:36:54Z. Current time: 2021-09-11T07:57:49Z, a difference of 37255119 milliseconds.  Allowed clock skew: 0 milliseconds.'),(4078,'Internal Server Error',NULL,'io.jsonwebtoken.ExpiredJwtException','/api/product/index','500','2021-09-11 07:59:30','io.jsonwebtoken.ExpiredJwtException: JWT expired at 2021-09-10T21:36:54Z. Current time: 2021-09-11T07:59:30Z, a difference of 37356212 milliseconds.  Allowed clock skew: 0 milliseconds.\r\n	at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:448)\r\n	at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:550)\r\n	at io.jsonwebtoken.impl.DefaultJwtParser.parseClaimsJws(DefaultJwtParser.java:610)\r\n	at dev.fenix.application.api.util.JwtUtil.extractAllClaims(JwtUtil.java:36)\r\n	at dev.fenix.application.api.util.JwtUtil.extractClaim(JwtUtil.java:30)\r\n	at dev.fenix.application.api.util.JwtUtil.extractUsername(JwtUtil.java:22)\r\n	at dev.fenix.application.api.filters.JwtRequestFilter.doFilterInternal(JwtRequestFilter.java:37)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:103)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:89)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:91)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doHeadersAfter(HeaderWriterFilter.java:90)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:75)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:110)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:80)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:55)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:211)\r\n	at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:183)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:358)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:271)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:202)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:542)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:143)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:374)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)\r\n	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:888)\r\n	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1597)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1130)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.base/java.lang.Thread.run(Thread.java:832)\r\n',NULL,'JWT expired at 2021-09-10T21:36:54Z. Current time: 2021-09-11T07:59:30Z, a difference of 37356212 milliseconds.  Allowed clock skew: 0 milliseconds.'),(4079,'Internal Server Error',NULL,'io.jsonwebtoken.ExpiredJwtException','/api/product/index','500','2021-09-11 07:59:47','io.jsonwebtoken.ExpiredJwtException: JWT expired at 2021-09-10T21:36:54Z. Current time: 2021-09-11T07:59:47Z, a difference of 37373021 milliseconds.  Allowed clock skew: 0 milliseconds.\r\n	at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:448)\r\n	at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:550)\r\n	at io.jsonwebtoken.impl.DefaultJwtParser.parseClaimsJws(DefaultJwtParser.java:610)\r\n	at dev.fenix.application.api.util.JwtUtil.extractAllClaims(JwtUtil.java:36)\r\n	at dev.fenix.application.api.util.JwtUtil.extractClaim(JwtUtil.java:30)\r\n	at dev.fenix.application.api.util.JwtUtil.extractUsername(JwtUtil.java:22)\r\n	at dev.fenix.application.api.filters.JwtRequestFilter.doFilterInternal(JwtRequestFilter.java:37)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:103)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:89)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:91)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doHeadersAfter(HeaderWriterFilter.java:90)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:75)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:110)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:80)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:55)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:211)\r\n	at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:183)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:358)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:271)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:202)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:542)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:143)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:374)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)\r\n	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:888)\r\n	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1597)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1130)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.base/java.lang.Thread.run(Thread.java:832)\r\n',NULL,'JWT expired at 2021-09-10T21:36:54Z. Current time: 2021-09-11T07:59:47Z, a difference of 37373021 milliseconds.  Allowed clock skew: 0 milliseconds.'),(4080,'Internal Server Error',NULL,'java.lang.ArrayIndexOutOfBoundsException','/api/product/index','500','2021-09-11 09:31:24','java.lang.ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1\r\n	at dev.fenix.application.production.product.service.ProductService.getAllProducts(ProductService.java:59)\r\n	at dev.fenix.application.api.production.product.ProductResource.index(ProductResource.java:62)\r\n	at jdk.internal.reflect.GeneratedMethodAccessor109.invoke(Unknown Source)\r\n	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.base/java.lang.reflect.Method.invoke(Method.java:564)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:197)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:141)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:106)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:894)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1061)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:961)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)\r\n	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:898)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:626)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:733)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:113)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:327)\r\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.invoke(FilterSecurityInterceptor.java:115)\r\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.doFilter(FilterSecurityInterceptor.java:81)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:119)\r\n	at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:113)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:126)\r\n	at org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:81)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.authentication.AnonymousAuthenticationFilter.doFilter(AnonymousAuthenticationFilter.java:105)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(SecurityContextHolderAwareRequestFilter.java:149)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.savedrequest.RequestCacheAwareFilter.doFilter(RequestCacheAwareFilter.java:63)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.authentication.www.BasicAuthenticationFilter.doFilterInternal(BasicAuthenticationFilter.java:149)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at dev.fenix.application.api.filters.JwtRequestFilter.doFilterInternal(JwtRequestFilter.java:52)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:103)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:89)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:91)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doHeadersAfter(HeaderWriterFilter.java:90)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:75)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:110)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:80)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:55)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:211)\r\n	at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:183)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:358)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:271)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:202)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:542)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:143)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:374)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)\r\n	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:888)\r\n	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1597)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1130)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.base/java.lang.Thread.run(Thread.java:832)\r\n',NULL,'Index 1 out of bounds for length 1'),(4081,'Internal Server Error',NULL,'java.lang.ArrayIndexOutOfBoundsException','/api/product/index','500','2021-09-11 09:31:45','java.lang.ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1\r\n	at dev.fenix.application.production.product.service.ProductService.getAllProducts(ProductService.java:59)\r\n	at dev.fenix.application.api.production.product.ProductResource.index(ProductResource.java:62)\r\n	at jdk.internal.reflect.GeneratedMethodAccessor109.invoke(Unknown Source)\r\n	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.base/java.lang.reflect.Method.invoke(Method.java:564)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:197)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:141)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:106)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:894)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1061)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:961)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)\r\n	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:898)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:626)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:733)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:113)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:327)\r\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.invoke(FilterSecurityInterceptor.java:115)\r\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.doFilter(FilterSecurityInterceptor.java:81)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:119)\r\n	at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:113)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:126)\r\n	at org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:81)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.authentication.AnonymousAuthenticationFilter.doFilter(AnonymousAuthenticationFilter.java:105)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(SecurityContextHolderAwareRequestFilter.java:149)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.savedrequest.RequestCacheAwareFilter.doFilter(RequestCacheAwareFilter.java:63)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.authentication.www.BasicAuthenticationFilter.doFilterInternal(BasicAuthenticationFilter.java:149)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at dev.fenix.application.api.filters.JwtRequestFilter.doFilterInternal(JwtRequestFilter.java:52)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:103)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:89)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:91)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doHeadersAfter(HeaderWriterFilter.java:90)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:75)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:110)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:80)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:55)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:211)\r\n	at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:183)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:358)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:271)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:202)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:542)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:143)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:374)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)\r\n	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:888)\r\n	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1597)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1130)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.base/java.lang.Thread.run(Thread.java:832)\r\n',NULL,'Index 1 out of bounds for length 1'),(4082,'Internal Server Error',NULL,'java.lang.ArrayIndexOutOfBoundsException','/api/product/index','500','2021-09-11 09:31:55','java.lang.ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1\r\n	at dev.fenix.application.production.product.service.ProductService.getAllProducts(ProductService.java:59)\r\n	at dev.fenix.application.api.production.product.ProductResource.index(ProductResource.java:62)\r\n	at jdk.internal.reflect.GeneratedMethodAccessor109.invoke(Unknown Source)\r\n	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.base/java.lang.reflect.Method.invoke(Method.java:564)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:197)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:141)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:106)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:894)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1061)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:961)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)\r\n	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:898)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:626)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:733)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:113)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:327)\r\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.invoke(FilterSecurityInterceptor.java:115)\r\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.doFilter(FilterSecurityInterceptor.java:81)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:119)\r\n	at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:113)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:126)\r\n	at org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:81)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.authentication.AnonymousAuthenticationFilter.doFilter(AnonymousAuthenticationFilter.java:105)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(SecurityContextHolderAwareRequestFilter.java:149)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.savedrequest.RequestCacheAwareFilter.doFilter(RequestCacheAwareFilter.java:63)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.authentication.www.BasicAuthenticationFilter.doFilterInternal(BasicAuthenticationFilter.java:149)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at dev.fenix.application.api.filters.JwtRequestFilter.doFilterInternal(JwtRequestFilter.java:52)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:103)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:89)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:91)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doHeadersAfter(HeaderWriterFilter.java:90)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:75)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:110)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:80)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:55)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:211)\r\n	at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:183)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:358)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:271)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:202)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:542)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:143)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:374)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)\r\n	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:888)\r\n	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1597)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1130)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.base/java.lang.Thread.run(Thread.java:832)\r\n',NULL,'Index 1 out of bounds for length 1'),(4083,'Internal Server Error',NULL,'java.lang.ArrayIndexOutOfBoundsException','/api/product/index','500','2021-09-11 09:37:47','java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0\r\n	at dev.fenix.application.production.product.service.ProductService.getFilters(ProductService.java:137)\r\n	at dev.fenix.application.production.product.service.ProductService.getAllProducts(ProductService.java:63)\r\n	at dev.fenix.application.api.production.product.ProductResource.index(ProductResource.java:62)\r\n	at jdk.internal.reflect.GeneratedMethodAccessor109.invoke(Unknown Source)\r\n	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.base/java.lang.reflect.Method.invoke(Method.java:564)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:197)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:141)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:106)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:894)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1061)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:961)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)\r\n	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:898)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:626)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:733)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:113)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:327)\r\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.invoke(FilterSecurityInterceptor.java:115)\r\n	at org.springframework.security.web.access.intercept.FilterSecurityInterceptor.doFilter(FilterSecurityInterceptor.java:81)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:119)\r\n	at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:113)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:126)\r\n	at org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:81)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.authentication.AnonymousAuthenticationFilter.doFilter(AnonymousAuthenticationFilter.java:105)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(SecurityContextHolderAwareRequestFilter.java:149)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.savedrequest.RequestCacheAwareFilter.doFilter(RequestCacheAwareFilter.java:63)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.authentication.www.BasicAuthenticationFilter.doFilterInternal(BasicAuthenticationFilter.java:149)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at dev.fenix.application.api.filters.JwtRequestFilter.doFilterInternal(JwtRequestFilter.java:52)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:103)\r\n	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:89)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:91)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doHeadersAfter(HeaderWriterFilter.java:90)\r\n	at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:75)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:110)\r\n	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:80)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:55)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n	at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:211)\r\n	at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:183)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:358)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:271)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:202)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:542)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:143)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:374)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)\r\n	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:888)\r\n	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1597)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1130)\r\n	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n	at java.base/java.lang.Thread.run(Thread.java:832)\r\n',NULL,'Index 0 out of bounds for length 0'),(4084,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:42:14',NULL,NULL,'Unauthorized'),(4085,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:42:30',NULL,NULL,'Unauthorized'),(4086,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:43:18',NULL,NULL,'Unauthorized'),(4087,'Unauthorized',NULL,NULL,'/api/192.168.3.82/api/product/index','401','2021-09-11 09:43:21',NULL,NULL,'Unauthorized'),(4088,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:43:28',NULL,NULL,'Unauthorized'),(4089,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:44:43',NULL,NULL,'Unauthorized'),(4090,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:44:47',NULL,NULL,'Unauthorized'),(4091,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:45:29',NULL,NULL,'Unauthorized'),(4092,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:46:25',NULL,NULL,'Unauthorized'),(4093,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:46:26',NULL,NULL,'Unauthorized'),(4094,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:46:43',NULL,NULL,'Unauthorized'),(4095,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:46:49',NULL,NULL,'Unauthorized'),(4096,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:48:09',NULL,NULL,'Unauthorized'),(4097,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:50:08',NULL,NULL,'Unauthorized'),(4098,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:50:41',NULL,NULL,'Unauthorized'),(4099,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:51:22',NULL,NULL,'Unauthorized'),(4100,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:51:41',NULL,NULL,'Unauthorized'),(4101,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:51:41',NULL,NULL,'Unauthorized'),(4102,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:51:50',NULL,NULL,'Unauthorized'),(4103,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:52:13',NULL,NULL,'Unauthorized'),(4104,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:52:28',NULL,NULL,'Unauthorized'),(4105,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:53:09',NULL,NULL,'Unauthorized'),(4106,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:53:20',NULL,NULL,'Unauthorized'),(4107,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:53:29',NULL,NULL,'Unauthorized'),(4108,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:54:25',NULL,NULL,'Unauthorized'),(4109,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:54:37',NULL,NULL,'Unauthorized'),(4110,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:55:43',NULL,NULL,'Unauthorized'),(4111,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:55:51',NULL,NULL,'Unauthorized'),(4113,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:59:14',NULL,NULL,'Unauthorized'),(4114,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 09:59:52',NULL,NULL,'Unauthorized'),(4115,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 10:00:54',NULL,NULL,'Unauthorized'),(4116,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 10:01:01',NULL,NULL,'Unauthorized'),(4117,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 10:01:03',NULL,NULL,'Unauthorized'),(4118,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 10:01:06',NULL,NULL,'Unauthorized'),(4119,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 10:02:03',NULL,NULL,'Unauthorized'),(4120,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 10:03:56',NULL,NULL,'Unauthorized'),(4121,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 10:04:12',NULL,NULL,'Unauthorized'),(4122,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 10:04:18',NULL,NULL,'Unauthorized'),(4123,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 10:04:22',NULL,NULL,'Unauthorized'),(4124,'Unauthorized',NULL,NULL,'/api/product/index','401','2021-09-11 10:05:52',NULL,NULL,'Unauthorized');
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
  PRIMARY KEY (`id`)
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
INSERT INTO `hibernate_sequence` VALUES (4125),(1),(1),(1),(1);
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
INSERT INTO `pe__person` VALUES (1,NULL,'2021-05-16 13:28:07','fayssal','benmoussa',NULL,2,NULL),(1889,NULL,'2021-08-24 11:31:12','issam','elyazri',NULL,1890,NULL),(1695,NULL,'2021-08-24 10:28:11','ZIZI','Mohamed',NULL,1696,NULL),(4074,'2021-02-10 23:00:00','2021-09-10 15:28:51','admin','admin','2021-02-10 23:00:00',4075,'MALE');
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
  PRIMARY KEY (`id`),
  KEY `FKrib8u9lq45r6tsag26rwii0md` (`parent_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prds__classification`
--

LOCK TABLES `prds__classification` WRITE;
/*!40000 ALTER TABLE `prds__classification` DISABLE KEYS */;
INSERT INTO `prds__classification` VALUES (1,'GC','crémes',0,NULL,1,''),(2,'GIP','intrants patisiers',0,NULL,1,''),(3,'GŒLF','œufs liquides frais',0,NULL,1,''),(4,'GŒLP','œufs liquides pasteurisés',0,NULL,1,''),(5,'GPF','préparation fromagère',0,NULL,1,''),(6,'GS','sauces',0,NULL,1,''),(7,'FAL','aromes liquides',1,2,1,'intrants patisiers'),(8,'FAP','aromes poudres',1,2,1,'intrants patisiers'),(9,'FCC','crémes chaudes',1,1,1,'crémes'),(10,'FCF','crémes froides',1,1,1,'crémes'),(11,'FDDS','decoration  de surface',1,2,1,'intrants patisiers'),(12,'FDDS','decoration  de surface',1,2,1,'intrants patisiers'),(13,'FF','fourrage',1,2,1,'intrants patisiers'),(14,'FI','impérial',1,5,1,'préparation fromagère'),(15,'FMZ','mamie zakia',1,5,1,'préparation fromagère'),(16,'FM','mozzarel',1,5,1,'préparation fromagère'),(17,'FŒLFB','œufs liquides frais blancs',1,3,1,'œufs liquides frais'),(18,'FŒLFBM','œufs liquides frais blancs melange',1,3,1,'œufs liquides frais'),(19,'FŒLPB','œufs liquides pasteurisés blancs',1,4,1,'œufs liquides pasteurisés'),(20,'FŒLPE','œufs liquides pasteurisés entiers',1,4,1,'œufs liquides pasteurisés'),(21,'FŒLPJ','œufs liquides pasteurisés jaunes',1,4,1,'œufs liquides pasteurisés'),(22,'FSC','sauces chaudes',1,6,1,'sauces'),(23,'FSF','sauces froides',1,6,1,'sauces'),(24,'FTC','top chef',1,5,1,'préparation fromagère'),(25,'AFF','aromes fruits frais',2,7,1,'aromes liquides'),(26,'AFS','aromes fruits secs',2,7,1,'aromes liquides'),(27,'AGD','aromes goûts divers',2,7,1,'aromes liquides'),(28,'AGD','aromes goûts divers',2,8,1,'aromes poudres'),(29,'APA','aromes plantes aromatiques',2,7,1,'aromes liquides'),(30,'APA','aromes plantes aromatiques',2,8,1,'aromes poudres'),(31,'CDC','créme de cuisson',2,9,1,'crémes chaudes'),(32,'CDG','créme de glace',2,10,1,'crémes froides'),(33,'CDP','crémes de patisserie',2,10,1,'crémes froides'),(34,'DFE','emulsifiants',2,13,1,'fourrage'),(35,'DSG','glaçage',2,12,1,'decoration  de surface'),(36,'DSG','glaçage',2,11,1,'decoration \n de surface'),(37,'IP','impérial rouge',2,14,1,'impérial'),(38,'IP','impérial rouge',2,14,1,'impérial'),(39,'MZ','mamie zakia',2,15,1,'mamie zakia'),(40,'MZ','mamie zakia',2,15,1,'mamie zakia'),(41,'MS','mamie zakia salade',2,15,1,'mamie zakia'),(42,'MB','mozzarel',2,16,1,'mozzarel'),(43,'MJ','mozzarel jaune',2,16,1,'mozzarel'),(44,'MJ','mozzarel jaune',2,16,1,'mozzarel'),(45,'DSN','nappage',2,12,1,'decoration  de surface'),(46,'DSN','nappage',2,11,1,'decoration \n de surface'),(47,'OFB','œufs liquides frais blanc',2,17,1,'œufs liquides frais blancs'),(48,'OPB','œufs liquides pasteurisés blancs',2,19,1,'œufs liquides pasteurisés blancs'),(49,'OPE','œufs liquides pasteurisés entiers',2,20,1,'œufs liquides pasteurisés entiers'),(50,'OPE','œufs liquides pasteurisés entiers',2,20,1,'œufs liquides pasteurisés entiers'),(51,'OPJ','œufs liquides pasteurisés jaunes',2,21,1,'œufs liquides pasteurisés jaunes'),(52,'OFM','œufs liquidesfrais melange',2,18,1,'œufs liquides frais blancs melange'),(53,'SBA','sauces blanches assaisonnées',2,23,1,'sauces froides'),(54,'SBN','sauces blanches nature',2,23,1,'sauces froides'),(55,'SJA','sauces jaunes assaisonnées',2,22,1,'sauces chaudes'),(56,'SRA','sauces rouges assaisonnées',2,22,1,'sauces chaudes'),(57,'SRN1','sauces rouges nature',2,22,1,'sauces chaudes'),(58,'TE','top che type edam',2,24,1,'top chef'),(59,'TB','top chef',2,24,1,'top chef'),(60,'TJ','top chef jaune',2,24,1,'top chef'),(61,'TE','top chef type edam',2,24,1,'top chef'),(4062,'GD','ddddd',0,NULL,1,NULL);
/*!40000 ALTER TABLE `prds__classification` ENABLE KEYS */;
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
-- Table structure for table `prds__packaging`
--

DROP TABLE IF EXISTS `prds__packaging`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prds__packaging` (
  `id` bigint NOT NULL,
  `active` tinyint(1) DEFAULT '1',
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prds__packaging`
--

LOCK TABLES `prds__packaging` WRITE;
/*!40000 ALTER TABLE `prds__packaging` DISABLE KEYS */;
INSERT INTO `prds__packaging` VALUES (1,1,'bidon de 2 kg de capacité'),(2,1,'bidon de 5 kg de capacité'),(3,1,'bidon en plastique'),(4,1,'bouteille en plastique'),(5,1,'film de cire et de cellophane'),(6,1,'film thermoformé'),(7,1,'film thermoformé \n(nouveau produit)'),(8,1,'film thermoformé \n(produit annulé de la gamme) \ndès le 24-06-21'),(9,1,'film thermoformé \n(produit spécifique à cosamia)'),(10,1,'film thermoformé  \n(produit spécifique à cosamia)'),(11,1,'flacon en plastique'),(12,1,'produit moulé'),(13,1,'sac plastique etanche et seau en plastique'),(14,1,'sac sous vide'),(15,1,'sac sous vide \n(nouveau produit)'),(16,1,'sac sous vide / \nfilm thermoformé'),(17,1,'sac sous vide / film thermoformé (nouveau produit)'),(18,1,'sac sous vide / filme thermoformé'),(19,1,'seau + sac de 21 kg de capacité'),(20,1,'seau de 10 kg'),(21,1,'seau de 20 kg'),(22,1,'seau des billes'),(23,1,'seau des boules'),(24,1,'seau en plastique');
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
  PRIMARY KEY (`id`),
  KEY `FKe1r4855lopehidmeclhss6n7v` (`classification_id`),
  KEY `FK77poyoi8drbt960lana4x72k2` (`packaging_id`),
  KEY `FKl17me8wmrt49sa0r21ngx37cc` (`product_type_id`),
  KEY `FKmr4f6dw2lm8674rvr7f9phy3n` (`production_unit_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prds__product`
--

LOCK TABLES `prds__product` WRITE;
/*!40000 ALTER TABLE `prds__product` DISABLE KEYS */;
INSERT INTO `prds__product` VALUES (1,'IMP','Impérial Mix En Seau Petite  Format De \n1,5 kg ','Impérial Mix 15 kg',34,24,1,1,1),(2,'IMM','Impérial Mix  En Seau Moyenne Format De 5 kg ','Impérial Mix 5 kg',34,24,1,1,1),(3,'AFP',' Aroma Plus Fruits passion En Bouteille De 1 kg ','Aroma Plus Fruits passion 1 kg',25,4,1,1,1),(4,'AAN','Aroma Plus Ananas  En Bouteille De \n1 kg ','Aroma Plus Ananas 1 kg',25,4,1,1,1),(5,'ABA','Aroma Plus Banane En Bouteille De  \n1 kg ','Aroma Plus Banane 1 kg',25,4,1,1,1),(6,'ACI','Aroma Plus Citron En Boutielle De \n1 kg ','Aroma Plus Citron 1 kg',25,4,1,1,1),(7,'ACV','Aroma Plus Citron Vert En Bouteille De \n1 kg ','Aroma Plus Citron Vert 1 kg',25,4,1,1,1),(8,'AFS','Aroma Plus Fraise En Bouteille De \n1 kg ','Aroma Plus Fraise 1 kg',25,4,1,1,1),(9,'AFM','Aroma Plus Framboise En Bouteille De \n1 kg ','Aroma Plus Framboise 1 kg',25,4,1,1,1),(10,'AFR','Aroma Plus Fruits Rouge En Bouteille De 1 kg ','Aroma Plus Fruits rouge 1 kg',25,4,1,1,1),(11,'AAM','Aroma Plus Amande En Bouteille De \n1 kg ','Aroma Plus Amande 1 kg',26,4,1,1,1),(12,'ANO','Aroma Plus NOUGAT En Bouteille De \n1 kg ','Aroma Plus NOUGAT 1 kg',26,4,1,1,1),(13,'API','Aroma Plus PISTACHE En Bouteille De 1 kg ','Aroma Plus PISTACHE 1 kg',26,4,1,1,1),(14,'ABE','Aroma Plus Beurre En Bouteille DE \n1 kg ','Aroma Plus Beurre 1 kg',27,4,1,1,1),(15,'ABU','Aroma Plus Bubbl GUM En Bouteille De 1 kg ','Aroma Plus Bubbl GUM 1 kg',27,4,1,1,1),(16,'ACA','Aroma Plus Cafe En Bouteille De \n1 kg ','Aroma Plus Cafe 1 kg',29,4,1,1,1),(17,'ACP','Aroma Plus Cappuccino En Bouteille De 1 kg ','Aroma Plus Cappuccino 1 kg',29,4,1,1,1),(18,'ACR','Aroma Plus Caramel En Bouteille De \n1 kg ','Aroma Plus Caramel 1 kg',29,4,1,1,1),(19,'AMT','Aroma Plus Menthe En Bouteille De \n1 kg ','Aroma Plus Menthe 1 kg',29,4,1,1,1),(20,'AVA','Aroma Plus Vanille En Bouteille De \n1 kg ','Aroma Plus Vanille 1 kg',29,4,1,1,1),(21,'AYP','Aroma Plus Yaourt Poudre En Seau De \n1 kg ','Arome Plus Yaourt Poudre 1 kg',27,24,1,1,1),(22,'AVP','Aroma Plus Vanille poudre En Seau De \n1 kg ','Arome Plus Vanille poudre 1 kg',29,24,1,1,1),(23,'CCG ','Créme Cosamia En Seau Grande Format De 10 kg ','Créma Cosamia 10 kg',31,24,1,1,1),(24,'CCB','Créme Cosamia En Bidon Grande Format De 5 kg ','Créma Cosamia 5 kg',31,3,1,1,1),(25,'CDM','Créma Délice En Bidon Moyenne Format De 2 L ','Créma délice 2 L',31,3,1,1,1),(26,'CDG','Créma Délice En Bidon Grande Format De 5 L ','Créma délice 5 L',31,3,1,1,1),(27,'CIM','Créma Ice En Seau Moyenne Format De 5 kg ','Créma Ice 5 L',32,24,1,1,1),(28,'CFM','Créma Frais En Seau Moyenne Format De 4,8 kg ','Créma Frais 5 L',33,24,1,1,1),(29,'CPM','Créma Pâte En Seau Moyenne Format De 5 kg ','Créma Pâte 5 kg',33,24,1,1,1),(30,'CCM ','Créma Plus Classique En Seau Moyenne Format De 4,8 kg ','Créma Plus Classique 5 L',33,13,1,1,1),(31,'COM','Créma Plus Originale En Seau Moyenne Format De 4,8 kg ','Créma Plus Originale 5 L',33,13,1,1,1),(32,'IBM','Impérial Blanc En Seau Moyenne Format De 4 kg ','Imperial Mirroir Blanc 4 kg',35,24,1,1,1),(33,'ICM','Impérial Caramel En Seau Moyenne Format De 4 kg ','Imperial Mirroir Caramel 4 kg',35,24,1,1,1),(34,'IHM ','Impérial Chocolat En Seau Moyenne Format De 4 kg ','Imperial Mirroir Chocolat 4 kg',35,24,1,1,1),(35,'NNM','Nappage Neutre En Seau Moyenne Format De 4,3 kg ','Top Chef Nappage Neutre  43 kg',45,24,1,1,1),(36,'MDM','Sauce Moutarde Deliso\'s En Bidon Moyenne Format De 2 kg ','Deliso\'s Sauce Moutarde 2 kg',55,3,1,1,1),(37,'MXP','Sauces Moutarde Déliso\'s En Flacon Petite Format  De 300 gr ','Deliso\'s Sauce Moutarde 300 gr',55,11,1,1,1),(38,'MXG','Sauces Moutarde Déliso\'s En Flacon Grande Format  De 950 gr ','Deliso\'s Sauce Moutarde 950 gr',55,11,1,1,1),(39,'BXP','Sauces Barbecue Déliso\'s En Flacon Petite Format  De 300 gr ','Déliso\'s Sauce Barbecue 300 gr',56,11,1,1,1),(40,'BXG','Sauces Barbecue Déliso\'s En Flacon Grande Format  De 950 gr ','Déliso\'s Sauce Barbecue 950 gr',56,11,1,1,1),(41,'PDM','Sauce Piquante Deliso\'s En Bidon Moyenne Format De 2 kg ','Deliso\'s Sauce Piquante 2 kg',56,3,1,1,1),(42,'PXP','Sauces Piquante Déliso\'s En Flacon Petite Format  De 300 gr ','Deliso\'s Sauce Piquante 300 gr',56,11,1,1,1),(43,'PXG','Sauces Piquante Déliso\'s En Flacon Grande Format  De 950 gr ','Deliso\'s Sauce Piquante 950 gr',56,11,1,1,1),(44,'ZDG','Sauce Pizza Déliso\'s En Bidon Grande Format De 5 kg','Deliso\'s Pizza 5 kg',56,3,1,1,1),(45,'KXP','Sauce Ketchup Déliso\'s En Flacon Petite Format De 300 gr ','Deliso\'s Sauce Ketchup 300 gr',57,11,1,1,1),(46,'KXG','Sauce Ketchup Déliso\'s En Flacon Grande Format De 950 gr ','Deliso\'s Sauce Ketchup 950 gr',57,11,1,1,1),(47,'KDM ','Sauce Ketchup Déliso\'s En Bidon Moyenne Format De 2 kg ','Deliso\'s Sauce  Ketchup 2 kg',57,3,1,1,1),(48,'KDG','Sauce Ketchup Déliso\'s En Bidon Grande Format De 5 kg ','Deliso\'s Sauce Ketchup 5 kg',57,3,1,1,1),(49,'GXP','Sauces Algérienne Déliso\'s En Flacon Petite Format De 300 gr  ','Deliso\'s  Sauce Algérienne 300 gr',53,11,1,1,1),(50,'GDM','Sauces Algérienne Déliso\'s En Seau Moyenne Format  De 4,7 kg  ','Deliso\'s Sauce Algérienne 47 kg',53,24,1,1,1),(51,'GXG','Sauces Algérienne Déliso\'s En Flacon Grande Format  De 950 gr  ','Deliso\'s Sauce Algérienne 950 gr',53,11,1,1,1),(52,'AXP','Sauces Andalouse Déliso\'s En Flacon Petite Format De 300 gr  ','Deliso\'s Sauce Andalouse 300 gr',53,11,1,1,1),(53,'ADM','Sauces Andalouse Déliso\'s En Seau Moyenne Format  De 4,7 kg  ','Deliso\'s Sauce Andalouse 47 kg',53,24,1,1,1),(54,'AXG','Sauces Andalouse Déliso\'s En Flacon Grande Format  De 950 gr  ','Deliso\'s Sauce Andalouse 950 gr',53,11,1,1,1),(55,'RXP','Sauces Biggy Burger Déliso\'s En Flacon Petite Format De 300 gr  ','Deliso\'s Sauce Biggy Burger 300 gr',53,11,1,1,1),(56,'RDM','Sauces Biggy Burger Déliso\'s En Seau Moyenne Format  De 4,7 kg  ','Deliso\'s Sauce Biggy Burger 47 kg',53,24,1,1,1),(57,'RXG','Sauces Biggy Burger Déliso\'s En Flacon Grande Format  De 950 gr  ','Deliso\'s Sauce Biggy Burger 950 gr',53,11,1,1,1),(58,'CXP','Sauces Cheezy Déliso\'s En Flacon Petite Format De 300 gr  ','Deliso\'s Sauce Cheezy 300 gr',53,11,1,1,1),(59,'CXG','Sauces Cheezy Déliso\'s En Flacon Grande Format  De 950 gr  ','Deliso\'s Sauce Cheezy 950 gr',53,11,1,1,1),(60,'TXP','Sauces Pita Déliso\'s En Flacon Petite Format De 300 gr  ','Deliso\'s Sauce Pita 300 gr',53,11,1,1,1),(61,'TXG','Sauces Pita Déliso\'s En Flacon Grande Format  De 950 gr  ','Deliso\'s Sauce Pita 950 gr',53,11,1,1,1),(62,'SXP','Sauces Samourai Déliso\'s En Flacon Petite Format De 300 gr  ','Deliso\'s Sauce Samourai 300 gr',53,11,1,1,1),(63,'SXG','Sauces Samourai Déliso\'s En Flacon Grande Format  De 950 gr  ','Deliso\'s Sauce Samourai 950 gr',53,11,1,1,1),(64,'YDG','Sauces Mayonnaise Déliso\'s En Bidon Grande Format  De 2 kg  ','Deliso\'s Sauce Mayonnaise 2 kg',54,3,1,1,1),(65,'YXP','Sauces Mayonnaise Déliso\'s En Flacon Petite Format  De 300 kg  ','Deliso\'s Sauce Mayonnaise 300 gr',54,11,1,1,1),(66,'YXG','Sauces Mayonnaise Déliso\'s En Flacon Grande Format  De 950 kg  ','Deliso\'s Sauce Mayonnaise 950 gr',54,11,1,1,1),(67,'IMV','Impérial Mix En Seau De 20 kg ','Impérial Mix Vrac',34,21,1,2,1),(68,'FPV',' Aroma Plus Fruits passion En Seau De 10 kg ','Aroma Plus Fruits passion Vrac',25,20,1,2,1),(69,'ANV','Aroma Plus Ananas  En Seau De 10 kg ','Aroma Plus Ananas Vrac',25,20,1,2,1),(70,'BAV','Aroma Plus Banane En Seau De 10 kg ','Aroma Plus Banane Vrac',25,20,1,2,1),(71,'CVV','Aroma Plus Citron Vert En Seau De 10 kg ','Aroma Plus Citron Vert Vrac',25,20,1,2,1),(72,'CIV','Aroma Plus Citron En Seau De 10 kg ','Aroma Plus Citron Vrac',25,20,1,2,1),(73,'FSV','Aroma Plus Fraise En Seau De 10 kg ','Aroma Plus Fraise Vrac',25,20,1,2,1),(74,'FMV','Aroma Plus Framboise En Seau De 10 kg ','Aroma Plus Framboise Vrac',25,20,1,2,1),(75,'FRV','Aroma Plus Fruits Rouge En Seau De 10 kg ','Aroma Plus Fruits rouge Vrac',25,20,1,2,1),(76,'AMV','Aroma Plus Amande En Seau De 10 kg ','Aroma Plus Amande Vrac',26,20,1,2,1),(77,'NOV','Aroma Plus NOUGAT En Seau De 10 kg ','Aroma Plus NOUGAT Vrac',26,20,1,2,1),(78,'PIV','Aroma Plus PISTACHE En Seau De 10 kg ','Aroma Plus PISTACHE Vrac',26,20,1,2,1),(79,'BEV','Aroma Plus Beurre En Seau  DE 10 kg ','Aroma Plus Beurre Vrac',27,20,1,2,1),(80,'BUV','Aroma Plus Bubbl GUM En Seau De 10 kg ','Aroma Plus Bubbl GUM Vrac',27,20,1,2,1),(81,'CAV','Aroma Plus Cafe En Seau De 10 kg ','Aroma Plus Cafe Vrac',29,20,1,2,1),(82,'CPV','Aroma Plus Cappuccino En Seau De 10 kg ','Aroma Plus Cappuccino Vrac',29,20,1,2,1),(83,'CRV','Aroma Plus Caramel En Seau De 10 kg ','Aroma Plus Caramel Vrac',29,20,1,2,1),(84,'MTV','Aroma Plus Menthe En Seau De 10 kg ','Aroma Plus Menthe Vrac',29,20,1,2,1),(85,'VAV','Aroma Plus Vanille En Seau De 10 kg ','Aroma Plus Vanille Vrac',29,20,1,2,1),(86,'YPV','Aroma Plus Yaourt Poudre En Seau De 10 kg ','Arome Plus Yaourt Poudre Vrac',27,20,1,2,1),(87,'VPV','Aroma Plus Vanille poudre En Seau De 10 kg ','Arome Plus Vanille poudre Vrac',29,20,1,2,1),(88,'CVC','Créma Cosamia Vrac En Seau De 20 kg','Créma Cosamia Vrac',31,21,1,2,1),(89,'CDV','Créma Délice Vrac En Seau De 20 kg','Créma délice Vrac',31,21,1,2,1),(90,'CIV','Aroma Plus Citron En Seau De 10 kg ','Aroma Plus Citron Vrac',32,21,1,2,1),(91,'CFV','Créma Frais Vrac En Seau De 20 kg','Créma Frais Vrac',33,21,1,2,1),(92,'CPV','Aroma Plus Cappuccino En Seau De 10 kg ','Aroma Plus Cappuccino Vrac',33,21,1,2,1),(93,'CCV','Créma Classique Vrac En Seau De 20 kg','Créma Plus Classique Vrac',33,21,1,2,1),(94,'COV','Créma Originale Vrac En Seau De 20 kg','Créma Plus Originale Vrac',33,21,1,2,1),(95,'IBV','Impérial Blanc En Seau De 20 kg ','Imperial Mirroir Blanc Vrac',35,21,1,2,1),(96,'ICV','Impérial Caramel En Seau De 20 kg ','Imperial Mirroir Caramel Vrac',35,21,1,2,1),(97,'IHV','Impérial Chocolat En Seau De 20 kg ','Imperial Mirroir Chocolat Vrac',35,21,1,2,1),(98,'NNV  ','Nappage Neutre En Seau De 20 kg ','Top Chef Nappage Neutre Vrac',45,21,1,2,1),(99,'SMV','Sauce Moutard En Seau De 20 kg','Deliso\'s Sauce Moutarde Vrac',55,21,1,2,1),(100,'SBV','Sauce Barbecue En Seau De 20 kg ','Déliso\'s Sauce Barbecue Vrac',56,21,1,2,1),(101,'SPV','Sauce Piquante En Seau De 20 kg ','Deliso\'s Sauce Piquante Vrac',56,21,1,2,1),(102,'SZV','Sauce Pizza En Seau De 20 kg ','Deliso\'s Sauce Pizza Vrac',56,21,1,2,1),(103,'SKV ','Sauce Ketchup En Seau De 20 kg ','Deliso\'s Sauce Ketchup Vrac',57,21,1,2,1),(104,'SGV','Sauce Algérienne En Seau De 20 kg ','Deliso\'s Sauce Algérienne Vrac',53,21,1,2,1),(105,'SAV','Sauce Andalouse En Seau De 20 kg ','Deliso\'s Sauce Andalouse Vrac',53,21,1,2,1),(106,'SRV','Sauce Biggy Burger En Seau De 20 kg ','Deliso\'s Sauce Biggy Burger Vrac',53,21,1,2,1),(107,'SCV','Sauce Cheezy En Seau De 20 kg ','Deliso\'s Sauce Cheezy Vrac',53,21,1,2,1),(108,'STV','Sauce Pita En Seau De 20 kg ','Deliso\'s Sauce Pita Vrac',53,21,1,2,1),(109,'SSV','Sauce Samourai En Seau De 20 kg ','Deliso\'s Sauce Samourai Vrac',53,21,1,2,1),(110,'SYV ','Sauce Mayonnaise En Seau De 20 kg ','Deliso\'s Sauce Mayonnaise Vrac',54,21,1,2,1),(111,'IEP','Impérial Entier Petite Format En Film de Cire Et De Cellophane','Boule Impérial 09 kg',37,5,2,1,1),(112,'IEM','Impérial Entier Moyenne Format En Film de Cire Et De Cellophane','Boule Impérial 1 5 kg',37,5,2,1,1),(113,'IEG','Impérial Entier Grande Format En Film de Cire Et De Cellophane ','Boule Impérial 1 7 kg',37,5,2,1,1),(114,'IPP','Impérial Portion Petite Format En Sac Sous Vide Indivuduel de 100 gr  ','Portion Impérial 100 gr',37,14,2,1,1),(115,'IPG ','Impérial Portion Grande Format En Sac Sous Vide Indivuduel de 100 gr','Portion Impérial 170 gr',37,14,2,1,1),(116,'MBI ','Mamie Zakia Billes En Seau Plastique Etanche De 1 kg ','Mamie Zakia Salade Billes 1 kg',41,22,2,1,1),(117,'MBO','Mamie Zakia Boules En Seau Plastique Etanche De 1 kg  ','Mamie Zakia Salade Boules 1 kg',41,23,2,1,1),(118,'MRG','Mamie Zakia Râpé Grande Format En Sac Sous Vide De 1 kg  ','Mamie Zakia  Râpé 1 kg',39,14,2,1,1),(119,'MBG','Mamie Zakia Bloc Grande Format En Film Thermoformé Sous Vide  De 1 kg ','Mamie Zakia Bloc 1 kg',39,6,2,1,1),(120,'MBG','Mamie Zakia Bloc Grande Format En Film Thermoformé Sous Vide  De 1 kg ','Mamie Zakia Bloc 1 kg',42,6,2,1,1),(121,'MPM','Mozzarel Portion Moyenne Format En Film Thermoformé Sous Vide De 200 gr  ','Mozzarel Portion 200 gr',42,6,2,1,1),(122,'MPP','Mozzarel Portion Petite Format En Sac Sous Vide Individuel De 90 gr ','Mozzarel Portion 90 gr',42,16,2,1,1),(123,'MRG','Mamie Zakia Râpé Grande Format En Sac Sous Vide De 1 kg  ','Mamie Zakia  Râpé 1 kg',42,14,2,1,1),(124,'MRP','Mozzarel Râpé Petite Format En Sac Sous Vide De 200 gr ','Mozzarel Râpé 200 gr',42,15,2,1,1),(125,'MJB ','Mozzarel Jaune Bloc Grande Format En Film Thermoformé Sous Vide  De 1 kg ','Mozzarel Jaune Bloc  1 kg',43,6,2,1,1),(126,'MJR ','Mozzarel Jaune Râpé Grande Format En Sac Sous Vide De 1 kg ','Mozzarel Jaune Râpé 1 kg',43,14,2,1,1),(127,'TRP','Top Chef Râpé Petite Format En Sac Sous Vide De 250 gr  ','Top Chef  Râpé 250 gr',59,14,2,1,1),(128,'TBG','Top Chef Bloc Grande Format En Film Thermoformé Sous Vide  De 1 kg ','Top Chef Bloc 1 kg',59,6,2,1,1),(129,'TBC','Top Chef Cosamia Blanche Bloc Grande Format En Film Thermoformé Sous Vide  De 1 kg ','Top Chef Cosamia Bloc 1 kg',59,9,2,1,1),(130,'TPP','Top Chef Portion Petite Format En Sac Sous Vide Individuel De 100 gr  ','Top Chef Portion 100 gr',59,18,2,1,1),(131,'TPG','Top Chef Portion Grande Format En Film Thermoformé Sous Vide De 250 gr ','Top Chef Portion 250 gr',59,6,2,1,1),(132,'TPC','Top Chef Portion Carré Grande Format En Film Thermoformé Sous Vide De 250 gr','Top Chef Portion Carré 250 gr',59,8,2,1,1),(133,'TRG','Top Chef Râpé Grande Format En Sac Sous Vide De 1 kg  ','Top Chef Râpé 1 kg',59,14,2,1,1),(134,'TJC ','Top Chef Cosamia Jaune Bloc Grande Format En Film Thermoformé Sous Vide  De 1 kg ','Top chef Cosamia Jaune Bloc 1 kg',60,10,2,1,1),(135,'TJB ','Top Chef Jaune Bloc Grande Format En Film Thermoformé Sous Vide  De 1 kg ','Top Chef Jaune Bloc 1 kg',60,6,2,1,1),(136,'TJR','Top Chef Jaune Râpé Grande Format En Sac Sous Vide De 1 kg  ','Top Chef Jaune Râpé 1 kg',60,14,2,1,1),(137,'EBG','Edam Bloc Grande Format En Film Thermoformé Sous Vide  De 1 kg','Top Chef Edam Bloc 1 kg',61,14,2,1,1),(138,'EPP','Edam portion Petite Format En Sac Sous Vide Individuel De 100 gr','Top Chef Edam Portion 100 gr',61,17,2,1,1),(139,'EPG','Edam Portion Grande Format En Film Thermoformé Sous Vide De 250 gr ','Top Chef Edam Portion 250 gr',61,7,2,1,1),(140,'ERG','Edam Râpé Grande Format En Sac Sous Vide De 1 kg  ','Top Chef Edam Râpé 1 kg',61,14,2,1,1),(141,'ERP','Edam Râpé Petite Format En Sac Sous Vide De 250 gr   ','Top Chef Edam Râpé 250 gr',61,15,2,1,1),(142,'BIPP','Boule Impérial Petite Format De 0,9 Kg','Boule Impérial 09 kg',38,12,2,2,1),(143,'BIPM','Boule Impérial Moyenne Format \nDe 1,5 kg ','Boule Impérial 1 5 kg',38,12,2,2,1),(144,'BIPG','Boule Impérial Grande Format De 1,7 kg ','Boule Impérial 17 kg',38,12,2,2,1),(145,'PIPP','Portion Impérial Petite Format De 100 gr ','Portion  Impérial 010 kg',38,12,2,2,1),(146,'PIPG','Portion Impérial Grande Format \nDe 170 gr','Portion Impérial 017 kg',38,12,2,2,1),(147,'BSMG','Bloc Semi-Fini De Mamie Zakia Blanche Grande Format En Piéce de 1 kg ','Bloc Mamie Zakia  1 kg',40,12,2,2,1),(148,'BIMS','Billes De Mamie Zakia En Seau De 1 kg ','Billes Mamie Zakia Salade 1 kg',41,12,2,2,1),(149,'BOMS','Boules De Mamie Zakia En Seau De 1 kg ','Boules Mamie Zakia Salade 1 kg',41,12,2,2,1),(150,'BSMJ ','Bloc Semi-Fini De Mozzarel Jaune Grande Format En Piéce De 1 kg ','Bloc Mozzarel Jaune 1 kg',44,12,2,2,1),(151,'BSMG','Bloc Semi-Fini De Mamie Zakia Blanche Grande Format En Piéce de 1 kg ','Bloc Mamie Zakia  1 kg',42,12,2,2,1),(152,'BSMP ','Bloc Semi-Fini De Mozzarel Blanche Pour Portions En Piéce de 1,1 kg ','Bloc Mozzarel 11 kg',42,12,2,2,1),(153,'BSEG','Bloc Semi-Fini De Edam Grande Format En Piéce de 1 kg ','Bloc Top Chef Edam 1 kg',58,12,2,2,1),(154,'BSEP','Bloc Semi-Fini De Edam Pour Portions En Piéce de 1,1 kg ','Bloc Top Chef Edam 11 kg',58,12,2,2,1),(155,'BSTG','Bloc Semi-Fini De Top Chef Blanche Grande Format En Piéce de 1 kg ','Bloc Top Chef  1 kg',59,12,2,2,1),(156,'BSTP','Bloc Semi-Fini De Top Chef Blanche Pour Portions En Piéce de 1,1 kg ','Bloc Top Chef 11 kg',59,12,2,2,1),(157,'BSTC','Bloc Semi-Fini De Top Chef Blanche Carré En Piéce de 1,3 kg ','Bloc Top Chef 13 kg',59,12,2,2,1),(158,'BSTJ','Bloc Semi-Fini De Top Chef Jaune Grande Format En Piéce De 1 kg ','Bloc Top Chef Jaune 1 kg',60,12,2,2,1),(159,'F02','Blancs D\'oeuf  Frais En Bidon De 2 kg ','Œufs Liquides Frais Blancs 2 kg',47,1,3,1,1),(160,'F05','Blancs D\'oeuf  Frais En Bidon De 5 kg ','Œufs Liquides Frais Blancs 5 kg',47,2,3,1,1),(161,'B20',' Blancs D\'œufs Pasteurisés En Sac Soudé De 20 kg ','Œufs Liquides Pasteurisés Blancs 20 kg',48,19,3,1,1),(162,'E10',' Entiers D\'œufs Pasteurisés En Sac Soudé De 10 kg ','Œufs Liquides Pasteurisés Entiers 10 kg',50,19,3,1,1),(163,'E15',' Entiers D\'œufs Pasteurisés En Sac Soudé De 15 kg ','Œufs Liquides Pasteurisés Entiers 15 kg',50,19,3,1,1),(164,'E16',' Entiers D\'œufs Pasteurisés En Sac Soudé De 16 kg ','Œufs Liquides Pasteurisés Entiers 16 kg',50,19,3,1,1),(165,'E17',' Entiers D\'œufs Pasteurisés En Sac Soudé De 17 kg ','Œufs Liquides Pasteurisés Entiers 17 kg',50,19,3,1,1),(166,'E02','Entiers D\'œufs Pasteurisés En Bidons De  2 kg ','Œufs Liquides Pasteurisés Entiers 2 kg',50,1,3,1,1),(167,'E20',' Entiers D\'œufs Pasteurisés En Sac Soudé De 20 kg ','Œufs Liquides Pasteurisés Entiers 20 kg',50,19,3,1,1),(168,'E21',' Entiers D\'œufs Pasteurisés En Sac Soudé De 21 kg ','Œufs Liquides Pasteurisés Entiers 21 kg',50,19,3,1,1),(169,'E05','Entiers D\'œufs Pasteurisés En Bidons De 5 kg ','Œufs Liquides Pasteurisés Entiers 5 kg',50,2,3,1,1),(170,'J02','Jaunes D\'œufs Pasteurisés En Bidons De 2 kg ','Œufs Liquides Pasteurisés Jaunes 2 kg',51,1,3,1,1),(171,'J20',' Jaunes D\'œufs Pasteurisés En Sac Soudé De 20 kg ','Œufs Liquides Pasteurisés Jaunes 20 kg',51,19,3,1,1),(172,'J05','Jaunes D\'œufs Pasteurisés En Bidons De 5 kg ','Œufs Liquides Pasteurisés Jaunes 5 kg',51,2,3,1,1),(173,'BVF','Blancs Vrac D\'œufs Liquides Frais En Seau De 20 kg ','Œufs Liquides Frais Blancs Vracs',47,21,3,2,1),(174,'MVF','Melanges Vrac De Blancs D\'Œufs Liquides Frais En Seau De 20 kg ','Œufs Liquides Frais Melange Vracs',52,21,3,2,1),(175,'BVP','Blancs Vrac D\'œufs Liquides Pasteurisés En Seau De 20 kg ','Œufs Liquides Pasteurisés Blancs Vracs',48,21,3,2,1),(176,'EVP','Entiers Vrac D\'oeufs Liquides Pasteurisés En Seau De 20 kg ','Œufs Liquides Pasteurisés Entiers Vracs',49,21,3,2,1),(177,'JVP','Jaunes Vrac D\'œufs Liquides Pasteurisés En Seau De 20 kg ','Œufs Liquides Pasteurisés Jaunes Vracs',51,21,3,2,1);
/*!40000 ALTER TABLE `prds__product` ENABLE KEYS */;
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
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prds__type`
--

LOCK TABLES `prds__type` WRITE;
/*!40000 ALTER TABLE `prds__type` DISABLE KEYS */;
INSERT INTO `prds__type` VALUES (1,'Produits Finis',1),(2,'Produits SemiFinis',1),(4064,'Matière première',1);
/*!40000 ALTER TABLE `prds__type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prds__unit`
--

DROP TABLE IF EXISTS `prds__unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prds__unit` (
  `id` bigint NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `active` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prds__unit`
--

LOCK TABLES `prds__unit` WRITE;
/*!40000 ALTER TABLE `prds__unit` DISABLE KEYS */;
INSERT INTO `prds__unit` VALUES (1,'FCI',1),(2,'FCII',1),(3,'OVO',1);
/*!40000 ALTER TABLE `prds__unit` ENABLE KEYS */;
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
INSERT INTO `sc__activity` VALUES (1050,'2021-06-08 14:37:21','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36'),(1051,'2021-06-08 14:37:24','0:0:0:0:0:0:0:1',2,'The user fenix has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36'),(1052,'2021-06-08 14:37:37','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenix1. Reason: Bad credentials','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36'),(1053,'2021-06-08 14:37:42','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36'),(1054,'2021-06-08 14:44:03','0:0:0:0:0:0:0:1',2,'The user fenix has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36'),(1055,'2021-06-08 14:44:09','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36'),(1056,'2021-06-08 14:44:13','0:0:0:0:0:0:0:1',2,'The user fenix has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36'),(1057,'2021-06-08 14:44:19','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36'),(1058,'2021-06-21 08:07:29','172.17.0.1',NULL,'Failed login attempt with username : fayssal.zf@gmail.com. Reason: Bad credentials','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36'),(1059,'2021-06-21 08:07:36','172.17.0.1',NULL,'Failed login attempt with username : fayssal.zf@gmail.com. Reason: Bad credentials','WARN','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36'),(1060,'2021-06-21 08:13:09','172.17.0.1',NULL,'Failed login attempt with username : fayssal.zf@gmail.com. Reason: Bad credentials','WARN','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36'),(1061,'2021-06-21 08:13:10','172.17.0.1',NULL,'Failed login attempt with username : fayssal.zf@gmail.com. Reason: Bad credentials','WARN','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36'),(1062,'2021-06-21 14:56:37','105.155.251.128',NULL,'Failed login attempt with username : admin. Reason: Bad credentials','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36'),(1063,'2021-06-22 11:05:50','105.155.251.128',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36'),(1066,'2021-06-22 11:41:05','105.155.251.128',NULL,'Failed login attempt with username : issam. Reason: User is disabled','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36'),(1067,'2021-06-22 11:41:59','105.155.251.128',1065,'The user issam has logged in.','INFO','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36'),(1085,'2021-06-23 08:19:08','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36'),(1087,'2021-06-23 08:19:24','192.168.3.59',1065,'The user issam has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36'),(1090,'2021-06-23 08:32:33','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36'),(1091,'2021-06-23 08:33:30','0:0:0:0:0:0:0:1',2,'The user fenix has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36'),(1092,'2021-06-23 08:52:38','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36'),(1093,'2021-06-23 08:54:00','0:0:0:0:0:0:0:1',2,'The user fenix has logged out','INFO','/business/team/index','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36'),(1094,'2021-06-23 09:11:36','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36'),(1108,'2021-07-13 08:01:02','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1109,'2021-07-13 08:13:36','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1111,'2021-07-13 09:20:45','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1112,'2021-07-13 09:41:02','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1151,'2021-07-16 08:02:20','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1152,'2021-07-16 08:13:24','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1154,'2021-07-16 09:26:50','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1155,'2021-07-16 09:27:08','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1156,'2021-07-16 09:31:27','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1157,'2021-07-16 09:31:29','0:0:0:0:0:0:0:1',2,'The user fenix has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1158,'2021-07-16 09:34:42','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1159,'2021-07-16 09:45:40','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1161,'2021-07-16 09:48:33','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1162,'2021-07-16 09:52:47','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1163,'2021-07-16 09:54:50','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1164,'2021-07-16 09:56:18','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1165,'2021-07-16 09:58:29','127.0.0.1',NULL,'Failed login attempt with username : . Reason: Bad credentials','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1166,'2021-07-16 09:58:35','127.0.0.1',2,'The user fenix has logged in.','INFO','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1167,'2021-07-16 10:00:15','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1168,'2021-07-16 10:01:21','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Linux; Android 6.0.1; Moto G (4)) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Mobile Safari/537.36'),(1169,'2021-07-16 10:02:19','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1170,'2021-07-16 10:03:47','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1171,'2021-07-16 10:04:09','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1172,'2021-07-16 10:05:37','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1173,'2021-07-16 10:06:58','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1174,'2021-07-16 10:09:26','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1175,'2021-07-16 10:10:47','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1176,'2021-07-16 10:12:01','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1177,'2021-07-16 10:14:32','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1178,'2021-07-16 10:15:33','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1179,'2021-07-16 10:17:07','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1180,'2021-07-16 10:19:48','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1181,'2021-07-16 10:21:13','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1182,'2021-07-16 10:26:14','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1183,'2021-07-16 10:32:50','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1184,'2021-07-16 10:35:50','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1185,'2021-07-16 10:37:49','127.0.0.1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1186,'2021-07-16 10:42:42','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1188,'2021-07-16 10:44:03','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1189,'2021-07-16 10:46:37','127.0.0.1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1190,'2021-07-16 10:48:19','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1191,'2021-07-16 10:50:26','127.0.0.1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1192,'2021-07-16 10:55:01','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1193,'2021-07-16 10:57:27','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1194,'2021-07-16 11:11:49','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1196,'2021-07-16 13:36:23','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1197,'2021-07-16 13:36:43','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Linux; Android 6.0.1; Moto G (4)) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Mobile Safari/537.36'),(1201,'2021-07-16 14:00:41','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1202,'2021-07-16 14:03:42','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1203,'2021-07-16 14:06:47','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1204,'2021-07-16 14:13:24','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1206,'2021-07-16 14:22:44','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1207,'2021-07-16 14:22:47','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1208,'2021-07-16 14:24:58','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'),(1209,'2021-07-16 14:28:18','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1210,'2021-07-16 14:31:22','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1211,'2021-07-16 14:33:04','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1212,'2021-07-16 14:34:42','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1213,'2021-07-16 14:36:14','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1214,'2021-07-16 14:37:20','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1215,'2021-07-16 14:41:53','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1216,'2021-07-16 14:43:11','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1217,'2021-07-16 14:45:51','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1219,'2021-07-16 14:47:29','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1220,'2021-07-16 14:50:40','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1223,'2021-07-16 15:02:17','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1224,'2021-07-16 15:21:13','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1225,'2021-07-16 15:23:09','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1226,'2021-07-16 15:34:34','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36'),(1227,'2021-08-02 08:57:35','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenixxx. Reason: User is disabled','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1228,'2021-08-02 08:57:40','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenix. Reason: Bad credentials','WARN','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1229,'2021-08-02 08:57:45','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenix. Reason: Bad credentials','WARN','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1230,'2021-08-02 08:57:50','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenix. Reason: Bad credentials','WARN','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1231,'2021-08-02 08:58:16','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenixxx. Reason: User is disabled','WARN','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1232,'2021-08-02 08:58:27','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenix. Reason: Bad credentials','WARN','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1233,'2021-08-02 08:58:38','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenix. Reason: Bad credentials','WARN','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1234,'2021-08-02 08:58:47','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1238,'2021-08-02 10:36:49','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1242,'2021-08-02 13:08:33','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1243,'2021-08-03 07:33:03','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1246,'2021-08-03 08:18:58','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1248,'2021-08-03 13:16:01','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1249,'2021-08-04 09:36:43','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1279,'2021-08-07 08:29:51','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenix. Reason: Bad credentials','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36'),(1280,'2021-08-07 08:29:57','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36'),(1283,'2021-08-09 08:57:20','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1284,'2021-08-09 08:59:16','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36'),(1285,'2021-08-09 09:08:41','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36'),(1286,'2021-08-09 09:12:28','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1287,'2021-08-09 09:16:00','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36'),(1288,'2021-08-09 09:22:07','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36'),(1290,'2021-08-09 09:58:17','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1291,'2021-08-09 15:38:02','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36'),(1292,'2021-08-11 07:29:27','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1294,'2021-08-12 10:07:25','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1295,'2021-08-12 11:07:30','127.0.0.1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36'),(1296,'2021-08-12 11:12:37','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36'),(1297,'2021-08-13 07:37:50','192.168.3.59',NULL,'Failed login attempt with username : issam. Reason: Bad credentials','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36'),(1298,'2021-08-13 07:37:54','192.168.3.59',1065,'The user issam has logged in.','INFO','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36'),(1307,'2021-08-16 08:36:05','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36'),(1315,'2021-08-16 08:38:39','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36'),(1559,'2021-08-23 07:46:04','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1660,'2021-08-24 07:51:44','192.168.3.59',1065,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1697,'2021-08-24 10:29:10','192.168.3.4',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36'),(1704,'2021-08-24 10:42:45','127.0.0.1',NULL,'Failed login attempt with username : fenix. Reason: Bad credentials','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1705,'2021-08-24 10:42:54','127.0.0.1',2,'The user fenix has logged in.','INFO','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1706,'2021-08-24 10:43:02','127.0.0.1',2,'The user fenix has logged out','INFO','/security/index','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1707,'2021-08-24 10:43:21','127.0.0.1',1696,'The user medzizi has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1711,'2021-08-24 10:44:12','0:0:0:0:0:0:0:1',1696,'The user medzizi has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1712,'2021-08-24 10:44:16','0:0:0:0:0:0:0:1',1696,'The user medzizi has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1722,'2021-08-24 10:46:35','0:0:0:0:0:0:0:1',1696,'The user medzizi has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1724,'2021-08-24 10:46:43','0:0:0:0:0:0:0:1',1696,'The user medzizi has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1891,'2021-08-24 11:38:30','0:0:0:0:0:0:0:1',1696,'The user medzizi has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1892,'2021-08-24 11:38:34','0:0:0:0:0:0:0:1',1696,'The user medzizi has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1936,'2021-08-25 11:06:00','192.168.3.59',1890,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1937,'2021-08-25 13:30:33','0:0:0:0:0:0:0:1',1696,'The user medzizi has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1938,'2021-08-25 13:30:48','192.168.3.59',1890,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1939,'2021-08-25 13:35:17','192.168.3.59',1890,'The user issam has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1940,'2021-08-25 13:35:24','192.168.3.59',1890,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1944,'2021-08-25 14:10:16','0:0:0:0:0:0:0:1',1696,'The user medzizi has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1945,'2021-08-25 14:12:52','192.168.3.59',1890,'The user issam has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(1946,'2021-08-25 14:18:27','192.168.3.59',1890,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(2008,'2021-08-27 13:26:55','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenix. Reason: Bad credentials','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(2009,'2021-08-27 13:27:06','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(2013,'2021-08-27 14:01:47','0:0:0:0:0:0:0:1',2,'The user fenix has logged out','INFO','/security/exception/index','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(2123,'2021-08-30 07:52:24','0:0:0:0:0:0:0:1',1696,'The user medzizi has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(2124,'2021-08-30 07:53:53','192.168.3.59',1890,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(2140,'2021-08-30 08:26:49','192.168.3.59',1890,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(2158,'2021-08-30 11:54:01','192.168.3.59',1890,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(2168,'2021-08-30 13:19:43','0:0:0:0:0:0:0:1',1696,'The user medzizi has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(2169,'2021-08-30 13:21:48','0:0:0:0:0:0:0:1',1696,'The user medzizi has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(2190,'2021-08-30 14:06:09','192.168.3.59',1890,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(2275,'2021-08-31 09:59:56','127.0.0.1',1696,'The user medzizi has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3229,'2021-09-03 14:09:21','0:0:0:0:0:0:0:1',1696,'The user medzizi has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3240,'2021-09-03 14:17:32','0:0:0:0:0:0:0:1',1696,'The user medzizi has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3459,'2021-09-04 09:06:57','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenix. Reason: Bad credentials','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3460,'2021-09-04 09:07:06','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3461,'2021-09-04 09:17:58','127.0.0.1',2,'The user fenix has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3464,'2021-09-04 09:21:13','0:0:0:0:0:0:0:1',3463,'The user admin has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3465,'2021-09-04 09:21:43','0:0:0:0:0:0:0:1',3463,'The user admin has logged out','INFO','/security/index','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3468,'2021-09-04 09:22:23','0:0:0:0:0:0:0:1',3463,'The user admin has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3469,'2021-09-04 09:22:48','0:0:0:0:0:0:0:1',3463,'The user admin has logged out','INFO','/security/index','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3499,'2021-09-06 07:28:39','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenix. Reason: Bad credentials','WARN','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3498,'2021-09-06 07:28:30','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenix. Reason: Bad credentials','WARN','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3472,'2021-09-04 09:23:20','0:0:0:0:0:0:0:1',3463,'The user admin has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3500,'2021-09-06 07:28:44','0:0:0:0:0:0:0:1',NULL,'Failed login attempt with username : fenixxx. Reason: Bad credentials','WARN','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3501,'2021-09-06 07:28:51','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login?error','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3831,'2021-09-08 08:40:04','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3835,'2021-09-08 10:10:56','192.168.3.59',1890,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(3836,'2021-09-08 10:58:14','127.0.0.1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Linux; Android 8.0; Pixel 2 Build/OPD3.170816.012) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Mobile Safari/537.36'),(3844,'2021-09-08 14:35:31','192.168.3.59',1890,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36'),(4058,'2021-09-10 13:15:36','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36'),(4066,'2021-09-10 14:52:15','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36'),(4067,'2021-09-10 14:52:25','0:0:0:0:0:0:0:1',2,'The user fenix has logged out','INFO','/','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36'),(4068,'2021-09-10 14:52:34','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36'),(4073,'2021-09-10 15:27:35','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36'),(4076,'2021-09-10 15:29:37','0:0:0:0:0:0:0:1',2,'The user fenix has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36'),(4112,'2021-09-11 09:56:28','192.168.3.59',1890,'The user issam has logged in.','INFO','/login','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36');
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
  `route` varchar(255) DEFAULT NULL,
  `order_num` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sc__routes`
--

LOCK TABLES `sc__routes` WRITE;
/*!40000 ALTER TABLE `sc__routes` DISABLE KEYS */;
INSERT INTO `sc__routes` VALUES (1,'boxes','Acceuil','/',1),(2,'layer-group','Produits','/item',2);
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
INSERT INTO `sc__routes_role` VALUES (1,1),(1,2),(2,1),(2,2),(3,1),(3,2),(4,1),(4,2),(5,1),(5,2),(6,1),(6,2);
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
INSERT INTO `sc__user_role` VALUES (2,1),(1696,1),(1696,2),(1890,1),(1890,2),(3463,1),(3463,2),(4030,1),(4030,2),(4075,1),(4075,2);
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
INSERT INTO `sc__users` VALUES (2,_binary '','2021-05-16 13:28:08','fayssal.note@gmail.com','2021-06-21 08:13:31','$2a$10$1hEDnt/sLkSYeeojtqcmk.k6N8kB2hyWXPmdcrvM4cGslaPYONZj.','yUPton5UmIXbBPbafpESI0HQPrgwr1','fenix','No_pass0'),(1890,_binary '','2021-08-24 11:31:12','issam@gmail.com',NULL,'$2a$12$dYNqs4Yy1MlFNC1qsXi1Ne.8vR2y4lJx8s5ujyF2p4nxpD4C8wIKm',NULL,'issam','123456'),(4075,_binary '','2021-09-10 15:28:51','admin@example.com',NULL,'$2a$12$EtwkE1VjTI1g7Shyt9ZkLO5nQ7AxBE0P65U1HKjYlti6Qs/uCCQx.','456454564','admin','password'),(1696,_binary '','2021-08-24 10:28:11','med.zizi@topchef.ma','2021-08-24 10:29:36','$2a$12$oOjUqT12HpGTi/9BLLR5R.em2.bKDEbgw2UobBBVDOMlX58HieGMW',NULL,'medzizi','medzizi');
/*!40000 ALTER TABLE `sc__users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendor__address`
--

DROP TABLE IF EXISTS `vendor__address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vendor__address` (
  `id` bigint NOT NULL,
  `active` varchar(255) NOT NULL,
  `address_one` varchar(255) NOT NULL,
  `address_ordinal` varchar(255) NOT NULL,
  `address_tow` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `locality` varchar(255) NOT NULL,
  `postcode` varchar(255) NOT NULL,
  `province` varchar(255) NOT NULL,
  `vendor` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendor__address`
--

LOCK TABLES `vendor__address` WRITE;
/*!40000 ALTER TABLE `vendor__address` DISABLE KEYS */;
/*!40000 ALTER TABLE `vendor__address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendor__classification`
--

DROP TABLE IF EXISTS `vendor__classification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vendor__classification` (
  `id` bigint NOT NULL,
  `active` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendor__classification`
--

LOCK TABLES `vendor__classification` WRITE;
/*!40000 ALTER TABLE `vendor__classification` DISABLE KEYS */;
/*!40000 ALTER TABLE `vendor__classification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendor__contact`
--

DROP TABLE IF EXISTS `vendor__contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vendor__contact` (
  `id` bigint NOT NULL,
  `active` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `note` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendor__contact`
--

LOCK TABLES `vendor__contact` WRITE;
/*!40000 ALTER TABLE `vendor__contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `vendor__contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendor__vendor`
--

DROP TABLE IF EXISTS `vendor__vendor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vendor__vendor` (
  `id` bigint NOT NULL,
  `active` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `note` varchar(255) NOT NULL,
  `social_reason` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `classement_id` bigint NOT NULL,
  `contact_id` bigint NOT NULL,
  `main_contact_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKius5kbqeetuk3ywadahwgblxs` (`classement_id`),
  KEY `FKa6dmfffoqubntnhho4emumgtq` (`contact_id`),
  KEY `FK8y46y4l7bxq7a799e2jvwbucg` (`main_contact_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendor__vendor`
--

LOCK TABLES `vendor__vendor` WRITE;
/*!40000 ALTER TABLE `vendor__vendor` DISABLE KEYS */;
/*!40000 ALTER TABLE `vendor__vendor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-12 18:59:31
