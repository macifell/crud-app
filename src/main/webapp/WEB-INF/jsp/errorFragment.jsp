<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<div class="panel panel-danger">
  <div class="panel-heading">Please correct the following errors in your submission</div>
  <div class="panel-body">
    <ul>
      <c:forEach items="${errors}" var="error">
        <li>${error}</li>
      </c:forEach>
    </ul>
  </div>
</div>