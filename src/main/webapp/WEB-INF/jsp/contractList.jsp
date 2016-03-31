<%@  page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="UTF-8"%>		
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="context" value="${pageContext.request.contextPath}" />

<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">
			Contract List<span class="badge">${contractList.size()}</span>
				<button class="btn btn-danger pull-right btn-xs" onclick= "return newContract();">
          			<span class="glyphicon glyphicon-plus-sign "></span> New Contract
          		</button>
        </div>
		<div class="panel-body" id="tablediv">
			<div class="table-responsive">
				<table id="tab" class="table table-striped table-hover table-responsive">
					<thead>
						<tr>
							<th  scope="col">Action</th> 
							<th  scope="col">Contract NBR</th>  
					        <th  scope="col">Start Date</th> 
					        <th  scope="col">End Date</th> 
					         
						</tr>
					</thead>

					<tbody id="content">
						
						<c:forEach items="${contractList}" var="cntct">
							    <tr>
							    <td> 
							    <c:choose>
							    <c:when test="${cntct.activeInd == 89}">
							    	<c:choose>
									 	
										 <c:when test="${cntct.referenceContract.insPrvdr.prvdr != null}">
											<a href="${context}/provider/${cntct.referenceContract.insPrvdr.prvdr.id}/contract/${cntct.id}"   rel='tab' >Edit</a> 
										</c:when>
										 <c:when test="${cntct.referenceContract.ins != null}">
											<a onclick ="return contract(${cntct.referenceContract.ins.id},${cntct.id})" href="#"   rel='tab' >Edit</a> 
										</c:when>
										<c:otherwise>
											issue
										</c:otherwise>
										</c:choose>
							    </c:when>
							    <c:otherwise> 
							    	<c:choose>
									
									 <c:when test="${cntct.referenceContractinsPrvdr.prvdr != null}">
										<a href="${context}/provider/${cntct.referenceContractinsPrvdr.prvdr.id}/contract/${cntct.id}"  href="#" rel='tab' >View</a>
									</c:when>
									 <c:when test="${cntct.referenceContract.ins != null}">
										<a href="${context}/insurance/${cntct.referenceContract.ins.id}/contract/${cntct.id}"  href="#"  rel='tab' >View</a>
									</c:when>
									<c:otherwise>
										issue
									</c:otherwise>
									</c:choose>
							    </c:otherwise>
							    </c:choose>
							    </td>
								   	<td> ${cntct.contractNBR}</td> 
						        	<td> ${cntct.startDate}</td>
						        	<td> ${cntct.endDate}</td>
						        	
						       </tr>     
						        
							</c:forEach>
						</tbody>
				</table>
			</div>
			<div class="col-md-12 text-center" id="page_navigation"></div>
			<div id="show_per_page"></div>
			<div id="current_page"></div>
		</div>
	</div>
</div>
