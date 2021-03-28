<template>
  <div style="width: 1000px">
    <h3><center>Offered Service</center></h3>
    <tr><center>
            <td>
                <input type="text" v-model="newPerson" placeholder="OfferedServiceId">
                <input type="text" v-model="newPerson" placeholder="OfferedServiceName">
                <input type="int" v-model="newPerson" placeholder="Duration">
                <input type="double" v-model="newPerson" placeholder="Price">
                <input type="text" v-model="newPerson" placeholder="Comments">
                <input type="text" v-model="newPerson" placeholder="ReminderDate">
                <input type="Time" v-model="newPerson" placeholder="ReminderTime">
            </td>
            <td>
                <button  @click="createPerson(newPerson)">Create OfferedService</button>
            </td></center>
    </tr>
    <vue-table-dynamic 
      :params="params" 
      ref="table"
    >
    </vue-table-dynamic>
    
  </div>

</template>

<script>
import VueTableDynamic from 'vue-table-dynamic'

const random = () => {
  return parseInt(Date.now() + Math.random() * 10000000).toString(16).slice(-6)
}

export default {
  name: 'Demo',
  data() {
    return {
      params: {
        data: [
          ['Index', `Data1`, `Data2`, `Data3`]
        ],
        header: 'row',
        edit: {
          row: [1],
          column: [1],
          cell: [[-1, -1]]
        },
        sort: [0,1,2,3],
        border: true,
        stripe: true,
        pagination: true,
        pageSize: 10,
        pageSizes: [5, 10, 20, 100],
        showCheck: true
      }
    }
  },
  mounted () {
    for (let i = 0; i < 100; i++) {
      this.params.data.push([i+1, `${random()}`, `${random()}`, `${random()}`])
    }
  },
  methods: {
    onCellChange (rowIndex, columnIndex, data) {
      console.log('onCellChange: ', rowIndex, columnIndex, data)
      console.log('table data: ', this.$refs.table.getData())
    },
    onSelect (isChecked, index, data) {
      console.log('onSelect: ', isChecked, index, data)
      console.log('Checked Data:', this.$refs.table.getCheckedRowDatas(true))
    },
    onSelectionChange (checkedDatas, checkedIndexs, checkedNum) {
      console.log('onSelectionChange: ', checkedDatas, checkedIndexs, checkedNum)
    }
  },
  components: { VueTableDynamic }
}
</script>

<style>
  #OfferedService{
    text-align: center;
  }
</style>