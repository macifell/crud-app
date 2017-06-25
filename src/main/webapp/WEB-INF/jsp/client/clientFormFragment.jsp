<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>

<input type="hidden" name="client.clientId" value="${clientForm.client.clientId}"/>

<div class="form-group row">
  <label
         class="control-label col-xs-12 col-sm-2 col-sm-offset-2 col-md-2 col-md-offset-3"
         for="companyName"
         >
    Company Name:
  </label>
  <div class="col-xs-12 col-sm-4 col-md-3">
    <input
           class="form-control"
           type="text"
           name="client.companyName"
           id="companyName"
           value="${clientForm.client.companyName}"
           />
  </div>
</div>

<div class="form-group row">
  <label
         class="control-label col-xs-12 col-sm-2 col-sm-offset-2 col-md-2 col-md-offset-3"
         for="websiteUri"
         >
    Website Uri:
  </label>
  <div class="col-xs-12 col-sm-4 col-md-3">
    <input
           class="form-control"
           type="text"
           name="client.websiteUri"
           id="websiteUri"
           value="${clientForm.client.websiteUri}"
           />
  </div>
</div>

<div class="form-group row">
  <label
         class="control-label col-xs-12 col-sm-2 col-sm-offset-2 col-md-2 col-md-offset-3"
         for="phoneNumber"
         >
    Phone Number:
  </label>
  <div class="col-xs-12 col-sm-4 col-md-3">
    <input
           class="form-control"
           type="text"
           name="client.phoneNumber"
           id="phoneNumber"
           value="${clientForm.client.phoneNumber}"
           />
  </div>
</div>

<div class="form-group row">
  <label
         class="control-label col-xs-12 col-sm-2 col-sm-offset-2 col-md-2 col-md-offset-3"
         for="mailingAddress"
         >
    Mailing Address:
  </label>
  <div class="col-xs-12 col-sm-4 col-md-3">
    <input
           class="form-control"
           type="text"
           name="client.mailingAddress"
           id="mailingAddress"
           value="${clientForm.client.mailingAddress}"
           />
  </div>
</div>

<div class="row">
  <label class="control-label col-xs-12 col-sm-2 col-sm-offset-2 col-md-2 col-md-offset-3">
   Contacts:
  </label>
</div>

<c:forEach items="${clientForm.people}" var="person">
  <div class="form-check row col-xs-12 col-sm-8 col-sm-offset-4 col-md-7 col-md-offset-5">
    <form:checkbox
      cssClass="form-check-input"
      path="clientForm.selectedPersonIds"
      value="${person.personId}"
      label="${person.firstName} ${person.lastName}"
      ></form:checkbox>
  </div>
</c:forEach>

<div class="row">
  <div class="col-xs-offset-6 col-sm-offset-8 col-md-offset-8">
    <a class="btn btn-warning" role="button" href="${pageContext.request.contextPath}/client/list">Cancel</a>
    <input class="btn btn-primary" type="submit" name="Submit" value="Submit"/>
  </div>
</div>
