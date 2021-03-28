<template>
  <div id="eventregistration">
    <h2>Persons</h2>
    <table>
      <t-header :header="tableHeaders"/>
      <t-row
        v-for="(row, $index) in tableRows"
        :key="$index"
        :row="row"
      />
      <tr v-for="person in persons" :key="person">
            <td>{{ person.name }}</td>
            <td>
              <ul>
                <li v-for="event in person.events" :key="event">
                  {{event.name}}
                </li>
              </ul>
            </td>
        </tr>
      <tr>
            <td>
                <input type="text" v-model="newPerson" placeholder="Person Name">
            </td>
            <td>
                <button v-bind:disabled="!newPerson" @click="createPerson(newPerson)">Create Person</button>
            </td>
      </tr>
    </table>
    <p>
      <span v-if="errorPerson" style="color:red">Error: {{errorPerson}}</span>
    </p>
  </div>
</template>

<script src="./registration.js">
</script>

<style>
  #eventregistration {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #f2ece8;
  }
</style>



