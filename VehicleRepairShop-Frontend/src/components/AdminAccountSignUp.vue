<template>
  <div class="adminAccountSignUp">
        <div>
        <b-navbar toggleable="lg" type="dark" variant="dark">
          <b-navbar-brand href="#/" style="color: black"
            >VRSS</b-navbar-brand
          >

          <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

          <b-collapse id="nav-collapse" is-nav>
            <b-navbar-nav>
              <b-nav-item href="#/" style="color: white"
                >Go Back</b-nav-item
              >
            </b-navbar-nav>
          </b-collapse>
        </b-navbar>
      </div>
    <br /><br />

    <h1 style="color: #409eff">Sign Up</h1>
    <h4 style="color: #000000">Create your account</h4>
    <br /><br />

    <table id="t01">
      <link
        rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css"
      />
      <tr>
        <th>Username:</th>
        <td><input type="text" v-model="username" /></td>
      </tr>

      <tr>
        <th>Name:</th>
        <td><input type="text" v-model="name" /></td>
      </tr>
      <tr v-if="!showPassword">
        <th>Password:</th>
        <td>
          <input
            v-bind:type="[showPassword ? 'text' : 'password']"
            v-model="password"
          />
          <i
            @click="showPassword = !showPassword"
            class="fa fa-eye-slash fa_custom"
          ></i>
        </td>
      </tr>
      <tr v-if="showPassword">
        <th>Password:</th>
        <td>
          <input
            v-bind:type="[showPassword ? 'text' : 'password']"
            v-model="password"
          />
          <i
            @click="showPassword = !showPassword"
            class="fa fa-eye fa_custom"
          ></i>
        </td>
      </tr>

      <tr v-if="!showConfirmPassword">
        <th>Confirm Password:</th>
        <td>
          <input
            v-bind:type="[showConfirmPassword ? 'text' : 'password']"
            v-model="confirmPassword"
          />
          <i
            @click="showConfirmPassword = !showConfirmPassword"
            class="fa fa-eye-slash fa_custom"
          ></i>
        </td>
      </tr>
      <tr v-if="showConfirmPassword">
        <th>Confirm Password:</th>
        <td>
          <input
            v-bind:type="[showConfirmPassword ? 'text' : 'password']"
            v-model="confirmPassword"
          />
          <i
            @click="showConfirmPassword = !showConfirmPassword"
            class="fa fa-eye fa_custom"
          ></i>
        </td>
      </tr>
    </table>

    <br /><br />
    <p>
      <span v-if="errorAdminAccount" style="color: red"
        >Error: {{ errorAdminAccount }}
      </span>
    </p>

    <button
      @click="goToAdminAccountLogin()"
      type="button"
      style="border-color: #909399; color: #909399"
      class="btn"
    >
      <font size="3"><b>Cancel</b></font>
    </button>
    <button
      @click="createAdminAccount(username, password, confirmPassword, name)"
      type="button"
      style="background-color: #409eff; color: white"
      class="btn"
    >
      <font size="3"><b>Sign Up</b></font>
    </button>

    <br /><br />
  </div>
</template>


<script src="./adminAccountSignUp.js">
</script>

<style>
#t01 {
  width: 20%;
  text-align: left;
  margin-left: auto;
  margin-right: auto;
}
.fa_custom {
  color: #409eff;
}
</style>