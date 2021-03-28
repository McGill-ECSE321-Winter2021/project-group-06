<template>
  <div class="edit-account">
    <p class="title">Manage Your Account</p>
    <p class="description">
      Here you can change your name and password.
    </p>
    <div class="flex-wrapper-one">
      <p class="username-label">Username:</p>
      <p class="username-text">&lt;enter username&gt;</p>
    </div>
    <div class="flex-wrapper-two">
      <p class="current-name-label">Current Name:</p>
      <p class="username-text">
        &lt;enter current name&gt;
      </p>
    </div>
    <div class="flex-wrapper-three">
      <p class="new-name-label">New Name:</p>
      <div class="new-name-field"></div>
    </div>
    <div class="flex-wrapper-four">
      <p class="new-password-label">New Password:</p>
      <div class="new-name-field"></div>
    </div>
    <div class="flex-wrapper-five">
      <div class="cancel-button">
        <p class="cancel-text">Cancel</p>
      </div>
      <div class="save-button">
        <p class="cancel-text">Save</p>
      </div>
    </div>
  </div>
</template>


<script>
</script>

<style lang="scss" scoped>
.edit-account {
  padding: 0 2px 0 0;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.title {
  height: 61px;
  width: 558px;
  font-family: "Helvetica";
  font-size: 45px;
  font-weight: 400;
  line-height: normal;
  color: rgba(0, 0, 0, 1);
  display: flex;
  margin-bottom: 10px;
}
.description {
  font-family: "Helvetica";
  font-size: 25px;
  font-weight: 400;
  line-height: normal;
  color: rgba(0, 0, 0, 1);
  margin-bottom: 54px;
  margin-left: 3px;
}
.flex-wrapper-one {
  margin-bottom: 26px;
  display: flex;
  align-items: center;
}
.username-label {
  height: 32px;
  width: 198px;
  font-family: "Helvetica";
  font-size: 25px;
  font-weight: 400;
  line-height: normal;
  color: rgba(0, 0, 0, 1);
  display: flex;
  margin-right: 16px;
}
.username-text {
  font-family: "Helvetica";
  font-size: 25px;
  font-weight: 400;
  line-height: normal;
  color: rgba(0, 0, 0, 1);
}
.flex-wrapper-two {
  margin-bottom: 23px;
  display: flex;
  align-items: flex-start;
}
.current-name-label {
  width: 198px;
  font-family: "Helvetica";
  font-size: 25px;
  font-weight: 400;
  line-height: normal;
  color: rgba(0, 0, 0, 1);
  margin-right: 16px;
}
.flex-wrapper-three {
  margin-bottom: 22px;
  display: flex;
  align-items: flex-start;
}
.new-name-label {
  height: 33px;
  width: 198px;
  font-family: "Helvetica";
  font-size: 25px;
  font-weight: 400;
  line-height: normal;
  color: rgba(0, 0, 0, 1);
  display: flex;
  margin-top: 3px;
  margin-right: 16px;
}
.new-name-field {
  width: 474px;
  height: 36px;
  background-color: rgba(255, 255, 255, 1);
  border-radius: 10px;
  border: 1px solid rgba(166, 166, 166, 1);
}
.flex-wrapper-four {
  margin-bottom: 56px;
  display: flex;
  align-items: center;
}
.new-password-label {
  width: 202px;
  font-family: "Helvetica";
  font-size: 25px;
  font-weight: 400;
  line-height: normal;
  color: rgba(0, 0, 0, 1);
  margin-right: 12px;
}
.flex-wrapper-five {
  padding: 0 0 0 304px;
  display: flex;
  align-items: flex-start;
}
.cancel-button {
  background-color: rgba(166, 166, 166, 1);
  margin-right: 24px;
  border-radius: 10px;
  padding: 14px 41px 13px;
  display: flex;
  align-items: center;
}
.cancel-text {
  font-family: "Helvetica";
  font-size: 30px;
  font-weight: 700;
  line-height: normal;
  color: rgba(255, 255, 255, 1);
}
.save-button {
  background-color: rgba(195, 0, 0, 1);
  border-radius: 10px;
  padding: 13px 55px 14px;
  display: flex;
  align-items: center;
}
</style>