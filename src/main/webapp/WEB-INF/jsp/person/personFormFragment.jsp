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

        <span
              class="input-group-addon"
              :class="{'alert-danger': !isFirstNameValid}"
              data-toggle="tooltip"
              data-container="body"
              :data-original-title="firstNameTooltip"
              >
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
      <div class="input-group">
        <input
               class="form-control"
               type="text"
               name="lastName"
               id="lastName"
               v-model="lastName"
               />

        <span
              class="input-group-addon"
              :class="{'alert-danger': !isLastNameValid}"
              data-toggle="tooltip"
              data-container="body"
              :data-original-title="lastNameTooltip"
              >
          <span class="glyphicon" :class="glyphicon(isLastNameValid)" aria-hidden="true"></span>
        </span>
      </div>
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
      <div class="input-group">
        <input
               class="form-control"
               type="text"
               name="emailAddress"
               id="emailAddress"
               v-model="emailAddress"
               />

        <span
              class="input-group-addon"
              :class="{'alert-danger': !isEmailAddressValid}"
              data-toggle="tooltip"
              data-container="body"
              :data-original-title="emailAddressTooltip"
              >
          <span class="glyphicon" :class="glyphicon(isEmailAddressValid)" aria-hidden="true"></span>
        </span>
      </div>
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
      <div class="input-group">
        <input
               class="form-control"
               type="text"
               name="streetAddress"
               id="streetAddress"
               v-model="streetAddress"
               />

        <span
              class="input-group-addon"
              :class="{'alert-danger': !isStreetAddressValid}"
              data-toggle="tooltip"
              data-container="body"
              :data-original-title="streetAddressTooltip"
              >
          <span class="glyphicon" :class="glyphicon(isStreetAddressValid)" aria-hidden="true"></span>
        </span>
      </div>
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
      <div class="input-group">
        <input
               class="form-control"
               type="text"
               name="city"
               id="city"
               v-model="city"
               />

        <span
              class="input-group-addon"
              :class="{'alert-danger': !isCityValid}"
              data-toggle="tooltip"
              data-container="body"
              :data-original-title="cityTooltip"
              >
          <span class="glyphicon" :class="glyphicon(isCityValid)" aria-hidden="true"></span>
        </span>
      </div>
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
      <div class="input-group">
        <input
               class="form-control"
               type="text"
               name="state"
               id="state"
               v-model="state"
               />

        <span
              class="input-group-addon"
              :class="{'alert-danger': !isStateValid}"
              data-toggle="tooltip"
              data-container="body"
              :data-original-title="stateTooltip"
              >
          <span class="glyphicon" :class="glyphicon(isStateValid)" aria-hidden="true"></span>
        </span>
      </div>
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
      <div class="input-group">
        <input
               class="form-control"
               type="text"
               name="zipCode"
               id="zipCode"
               v-model="zipCode"
               />

        <span
              class="input-group-addon"
              :class="{'alert-danger': !isZipCodeValid}"
              data-toggle="tooltip"
              data-container="body"
              :data-original-title="zipCodeTooltip"
              >
          <span class="glyphicon" :class="glyphicon(isZipCodeValid)" aria-hidden="true"></span>
        </span>
      </div>
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
      <input class="btn btn-primary" type="submit" name="Submit" value="Submit" :disabled="!isFormValid" />
    </div>
  </div>
</div>

<script>
var app = new Vue({
	el: '#person-form-app',

	data: {
		firstName: '${person.firstName}',
		lastName: '${person.lastName}',
		emailAddress: '${person.emailAddress}',
		streetAddress: '${person.streetAddress}',
		city: '${person.city}',
		state: '${person.state}',
		zipCode: '${person.zipCode}',

		firstNameError: 'First name is required with a maximum length of 50',
		lastNameError: 'Last name is required with a maximum length of 50',
		emailAddressError: 'Email address is required with a maximum length of 50',
		streetAddressError: 'Street address is required with a maximum length of 50',
		cityError: 'City is required with a maximum length of 50',
		stateError: 'State is required with a length of 2',
		zipCodeError: 'Zip Code is required with a length of 5'
	},

	computed: {
		isFirstNameValid: function () {
			return this.isLengthBetween(this.firstName, 1, 50)
		},
		
        isLastNameValid: function () {
			return this.isLengthBetween(this.lastName, 1, 50)
		},	

		isEmailAddressValid: function () {
			return this.isLengthBetween(this.emailAddress, 1, 50)
		},
		
		isStreetAddressValid: function () {
			return this.isLengthBetween(this.streetAddress, 1, 50)
		},
		
		isCityValid: function () {
			return this.isLengthBetween(this.city, 1, 50)
		},
		
		isStateValid: function () {
			return this.isLengthBetween(this.state, 2, 2)
		},
		
		isZipCodeValid: function () {
			return this.isLengthBetween(this.zipCode, 5, 5)
		},
		
		isFormValid: function () {
			return this.isFirstNameValid
			    && this.isLastNameValid
			    && this.isEmailAddressValid
			    && this.isStreetAddressValid
			    && this.isCityValid
			    && this.isStateValid
			    && this.isZipCodeValid
		},
		
        firstNameTooltip: function () {
		    return this.isFirstNameValid ? '' : this.firstNameError
		},

        lastNameTooltip: function () {
		    return this.isLastNameValid ? '' : this.lastNameError
		},
		
        emailAddressTooltip: function () {
		    return this.isEmailAddressValid ? '' : this.emailAddressError
		},
		
        streetAddressTooltip: function () {
		    return this.isStreetAddressValid ? '' : this.streetAddressError
		},
		
        cityTooltip: function () {
		    return this.isCityValid ? '' : this.cityError
		},
		
        stateTooltip: function () {
		    return this.isStateValid ? '' : this.stateError
		},
		
        zipCodeTooltip: function () {
		    return this.isZipCodeValid ? '' : this.zipCodeError
		}
	},

	methods: {
	    glyphicon: function (isValid) {
          if (isValid) {
            return 'glyphicon-ok'
          }

          return 'glyphicon-ban-circle'
        },
        
        isLengthBetween: function (text, minimumLength, maximumLength) {
        	return text.length >= minimumLength && text.length <= maximumLength;
        }
	}
})
</script>