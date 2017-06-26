<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>

<div id="person-form-app">
  <input type="hidden" name="personId" value="${person.personId}"/>

  <div class="form-group row">
    <label
           class="control-label col-xs-12 col-sm-2 col-sm-offset-2 col-md-2 col-md-offset-3"
           for="firstName"
           >
      First Name:
    </label>
    <div class="col-xs-12 col-sm-4 col-md-3">
      <div class="input-group">
        <input
               class="form-control"
               type="text"
               name="firstName"
               id="firstName"
               v-model="firstName"
               />

        <span class="input-group-addon" :class="{'alert-danger': !isFirstNameValid}">
          <span class="glyphicon" :class="glyphicon(isFirstNameValid)" aria-hidden="true"></span>
        </span>
      </div>
    </div>
  </div>

  <div class="form-group row">
    <label
           class="control-label col-xs-12 col-sm-2 col-sm-offset-2 col-md-2 col-md-offset-3"
           for="lastName"
           >
      Last Name:
    </label>
    <div class="col-xs-12 col-sm-4 col-md-3">
      <input
             class="form-control"
             type="text"
             name="lastName"
             id="lastName"
             value="${person.lastName}"
             />
    </div>
  </div>

  <div class="form-group row">
    <label
           class="control-label col-xs-12 col-sm-2 col-sm-offset-2 col-md-2 col-md-offset-3"
           for="emailAddress"
           >
      Email Address:
    </label>
    <div class="col-xs-12 col-sm-4 col-md-3">
      <input
             class="form-control"
             type="text"
             name="emailAddress"
             id="emailAddress"
             value="${person.emailAddress}"
             />
    </div>
  </div>

  <div class="form-group row">
    <label
           class="control-label col-xs-12 col-sm-2 col-sm-offset-2 col-md-2 col-md-offset-3"
           for="streetAddress"
           >
      Street Address:
    </label>
    <div class="col-xs-12 col-sm-4 col-md-3">
      <input
             class="form-control"
             type="text"
             name="streetAddress"
             id="streetAddress"
             value="${person.streetAddress}"
             />
    </div>
  </div>

  <div class="form-group row">
    <label
           class="control-label col-xs-12 col-sm-2 col-sm-offset-2 col-md-2 col-md-offset-3"
           for="city"
           >
      City:
    </label>
    <div class="col-xs-12 col-sm-4 col-md-3">
      <input
             class="form-control"
             type="text"
             name="city"
             id="city"
             value="${person.city}"
             />
    </div>
  </div>

  <div class="form-group row">
    <label
           class="control-label col-xs-12 col-sm-2 col-sm-offset-2 col-md-2 col-md-offset-3"
           for="state"
           >
      State:
    </label>
    <div class="col-xs-12 col-sm-4 col-md-3">
      <input
             class="form-control"
             type="text"
             name="state"
             id="state"
             value="${person.state}"
             />
    </div>
  </div>

  <div class="form-group row">
    <label
           class="control-label col-xs-12 col-sm-2 col-sm-offset-2 col-md-2 col-md-offset-3"
           for="zipCode"
           >
      Zip Code:
    </label>
    <div class="col-xs-12 col-sm-4 col-md-3">
      <input
             class="form-control"
             type="text"
             name="zipCode"
             id="zipCode"
             value="${person.zipCode}"
             />
    </div>
  </div>

  <div class="form-group row">
    <label
           class="control-label col-xs-12 col-sm-2 col-sm-offset-2 col-md-2 col-md-offset-3"
           for="client"
           >
      Client:
    </label>
    <div class="col-xs-12 col-sm-4 col-md-3">
      <form:select path="person.clientId" id="client" cssClass="form-control">
        <form:option value="">-- NONE --</form:option>
        <form:options items="${clients}" itemLabel="companyName" itemValue="clientId" />
      </form:select> 
    </div>
  </div>

  <div class="row">
    <div class="col-xs-offset-6 col-sm-offset-8 col-md-offset-8">
      <a class="btn btn-warning" role="button" href="${pageContext.request.contextPath}/person/list">Cancel</a>
      <input class="btn btn-primary" type="submit" name="Submit" value="Submit" :disabled="!isFormValid"/>
    </div>
  </div>
</div>

<script>
var app = new Vue({
	el: '#person-form-app',
	data: {
		firstName: '${person.firstName}',
	},
	computed: {
		isFirstNameValid: function () {
			return this.firstName.length > 0;
		},
		
		isFormValid: function () {
			return this.isFirstNameValid;
		}
	},
	methods: {
	    glyphicon: function (isValid) {
          if (isValid) {
            return 'glyphicon-ok'
          }

          return 'glyphicon-ban-circle'
        }
	}
})
</script>