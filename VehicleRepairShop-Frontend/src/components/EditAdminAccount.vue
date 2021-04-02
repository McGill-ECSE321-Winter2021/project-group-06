<template>
  <div class="editAdminAccount">
    <br /><br />
    <h1 style="color: #409eff">Manage Your Account</h1>
    <h4 style="color: #000000">Here you can change your name and password</h4>
    <!-- <br></br> -->

    <table id="t01">
      <link
        rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css"
      />
      <tr>
        <th>Username:</th>
        <td>{{ this.$currentUsername.value }}</td>
      </tr>
      <tr>
        <th>Current Name:</th>
        <td>{{ this.$currentName.value }}</td>
      </tr>
      <th>New Name:</th>
      <td><input type="text" v-model="newName" /></td>

      <tr v-if="!showPassword">
        <th>New Password:</th>
        <td>
          <input
            v-bind:type="[showPassword ? 'text' : 'password']"
            v-model="newPassword"
          />
          <i
            @click="showPassword = !showPassword"
            class="fa fa-eye-slash fa_custom"
          ></i>
        </td>
      </tr>
      <tr v-if="showPassword">
        <th>New Password:</th>
        <td>
          <input
            v-bind:type="[showPassword ? 'text' : 'password']"
            v-model="newPassword"
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
            v-bind:type="[showConfirmassword ? 'text' : 'password']"
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
    <br />
    <br />
    <p>
      <span v-if="errorAdminAccount" style="color: red"
        >Error: {{ errorAdminAccount }}
      </span>
    </p>

    <button
      @click="goToViewAdminAccount()"
      type="button"
      style="border-color: #909399; color: #909399"
      class="btn"
    >
      <font size="3"><b>Cancel</b></font>
    </button>

    <button
      @click="updateAdminAccount(newPassword, confirmPassword, newName)"
      type="button"
      style="background-color: #409eff; color: white"
      class="btn"
    >
      <font size="3"><b>Save</b></font>
    </button>
  </div>
</template>


<script  src="./editAdminAccount.js">
</script>

<style>
#t01 {
  width: 40%;
  text-align: left;
  margin-left: auto;
  margin-right: auto;
}
.fa_custom {
  color: #409eff;
}
</style>