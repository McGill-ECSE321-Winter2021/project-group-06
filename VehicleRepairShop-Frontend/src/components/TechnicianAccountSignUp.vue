<template>
  <div class="technicianAccountSignUp">
    <br><br/>

    <h1 style="color: #409EFF">Sign Up</h1>
    <h4 style="color: #000000">Create your account</h4>
    <br><br/>

    <table id="t01">
      <tr>
        <th>Username:</th>
        <td><input type="text" v-model="username" /></td>
      </tr>

      <tr>
        <th>Name:</th>
        <td><input type="text" v-model="name" /></td>
      </tr>

      <tr>
        <th>Password:</th>
        <td><input type="password" v-model="password" /></td>
      </tr>
    </table>

    <br><br/>
    <p>
      <span v-if="errorTechnicianAccount" style="color: red">Error: {{ errorTechnicianAccount }}
    </span>
    </p>

    <button
      @click="goToTechnicianAccountLogin()"
      type="button"
      style="border-color: #909399; color: #909399"
      class="btn"
    >
      <font size="3"><b>Cancel</b></font>
    </button>
    <button
      @click="createTechnicianAccount(username, password, name)"
      type="button"
      style="background-color: #409EFF; color: white"
      class="btn"
    >
      <font size="3"><b>Sign Up</b></font>
    </button>

    <br><br/>
  </div>
</template>


<script src="./technicianAccountSignUp.js">
</script>

<style>

#t01 {
  width: 20%;
  text-align: left;
  margin-left: auto;
  margin-right: auto;
}
</style>