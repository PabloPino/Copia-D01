<%--
 * action-2.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="marchs" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	
	<security:authorize access="hasRole('MEMBER')">
	<jstl:if test="${memberId==row.member.id}">
	<display:column>

				<a href="march/member/edit.do?marchId=${row.id}"> <spring:message
						code="march.edit" />
				</a>

			</display:column>
	</jstl:if>
	</security:authorize>
	<security:authorize access="hasRole('BROTHERHOOD')">
		<jstl:if test="${brotherhoodId==row.procession.brotherhood.id}">
			<display:column>

				<a href="march/brotherhood/edit.do?marchId=${row.id}"> <spring:message
						code="march.edit" />
				</a>

			</display:column>
		</jstl:if>
	</security:authorize>
	<jstl:if test="${march.status.equals(PENDING)}">
		<display:column property="status" titleKey="march.status"
			style="background-color:Grey" />
	</jstl:if>

	<jstl:if test="${march.status.equals(ACCEPTED) }">
		<display:column property="status" titleKey="march.status"
			style="background-color:Green" />
	</jstl:if>

	<jstl:if test="${march.status.equals(REJECTED)}">
		<display:column property="status" titleKey="march.status"
			style="background-color:Orange" />
	</jstl:if>
	<display:column property="reason" titleKey="march.reason" />
	<display:column property="location" titleKey="march.location" />
	




</display:table>



