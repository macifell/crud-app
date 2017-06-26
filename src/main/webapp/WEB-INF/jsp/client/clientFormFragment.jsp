<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>

<div id="client-form-app">
  <input type="hidden" name="client.clientId" value="${clientForm.client.clientId}"/>

  <div class="form-group row">
    <label
           class="control-label col-xs-12 col-sm-2 col-sm-offset-2 col-md-2 col-md-offset-3"
           for="companyName"
           >
      Company Name:
    </label>
    <div class="col-xs-12 col-sm-4 col-md-3">
      <div class="input-group">
        <input
               class="form-control"
               type="text"
               name="client.companyName"
               id="companyName"
               v-model="companyName"
               />

        <span class="input-group-addon" :class="{'alert-danger': !isCompanyNameValid}">
          <span class="glyphicon" :class="glyphicon(isCompanyNameValid)" aria-hidden="true"></span>
        </span>
      </div>
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
      <div class="input-group">
        <input
               class="form-control"
               type="text"
               name="client.websiteUri"
               id="websiteUri"
               v-model="websiteUri"
               />

        <span class="input-group-addon" :class="{'alert-danger': !isWebsiteUriValid}">
          <span class="glyphicon" :class="glyphicon(isWebsiteUriValid)" aria-hidden="true"></span>
        </span>
      </div>
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
      <div class="input-group">
        <input
               class="form-control"
               type="text"
               name="client.phoneNumber"
               id="phoneNumber"
               v-model="phoneNumber"
               />

        <span class="input-group-addon" :class="{'alert-danger': !isPhoneNumberValid}">
          <span class="glyphicon" :class="glyphicon(isPhoneNumberValid)" aria-hidden="true"></span>
        </span>
      </div>
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
      <div class="input-group">
        <input
               class="form-control"
               type="text"
               name="client.mailingAddress"
               id="mailingAddress"
               v-model="mailingAddress"
               />

        <span class="input-group-addon" :class="{'alert-danger': !isMailingAddressValid}">
          <span class="glyphicon" :class="glyphicon(isMailingAddressValid)" aria-hidden="true"></span>
        </span>
      </div>
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
      <input class="btn btn-primary" type="submit" name="Submit" value="Submit" :disabled="!isFormValid" />
    </div>
  </div>
</div>

<script>
var app = new Vue({
    el: '#client-form-app',

    data: {
        companyName: '${clientForm.client.companyName}',
        websiteUri: '${clientForm.client.websiteUri}',
        phoneNumber: '${clientForm.client.phoneNumber}',
        mailingAddress: '${clientForm.client.mailingAddress}'
    },

    computed: {
        isCompanyNameValid: function () {
            return this.isLengthBetween(this.companyName, 1, 50)
        },

        isWebsiteUriValid: function () {
            return this.isLengthBetween(this.websiteUri, 1, 50)
        },  

        isPhoneNumberValid: function () {
            return this.phoneNumber.match(/\d{3}-\d{3}-\d{4}$/)
        },
        
        isMailingAddressValid: function () {
            return this.isLengthBetween(this.mailingAddress, 1, 50)
        },
        
        isFormValid: function () {
            return this.isCompanyNameValid
                && this.isWebsiteUriValid
                && this.isPhoneNumberValid
                && this.isMailingAddressValid
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