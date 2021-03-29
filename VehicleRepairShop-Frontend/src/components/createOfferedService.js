
  function OfferedServiceDto(offeredServiceId,price,name,duration,reminderTime,reminderDate,description){
    this.offeredServiceId = offeredServiceId
    this.price = price
    this.name = name
    this.duration = duration
    this.reminderTime = reminderTime
    this.reminderDate= reminderDate
    this.description = description
  }
  
    import VueTableDynamic from 'vue-table-dynamic'
    export default {
    name: 'Demo',
    data() {
        return {
        params: {
            data: [
            ['Id', `Name`, `Price`, `Duration`, `Comments`,`ReminderDate`,`ReminderTime`]
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
        
        //this.params.data.push([i+1, newOfferedService.offeredServiceId, newOfferedService.name, newOfferedService.price, newOfferedService.duration, newOfferedService.comments, newOfferedService.reminderDate, newOfferedService.reminderTime])
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
        },
        createOfferedService: function (offeredServiceId, price,name,duration,reminderTime,reminderDate,description) {
            this.params.data.push([offeredServiceId, name, price, duration, description, reminderDate, reminderTime])
        },
        deleteOfferedService: function(rowIndex){
            delete this.tableData.SelectedRows[rowIndex]
        }
    },
    components: { VueTableDynamic }
    }


//   export default {
//     name: 'createOfferedService',
//     created: function () {
//       this.offeredServices = []
//     },
  
//     data () {
//       return {
//         offeredServices: [],
//         newOfferedService :'',
//         errorOfferedService :'',
//         response: []
//       }
//     },
  
    // methods: {
    //   createOfferedService: function (OfferedServiceId, price,name,duration,reminderTime,reminderDate,description) {
    //     // Create a new person and add it to the list of people
    //     // var o = new OfferedServiceDto(OfferedServiceId, price,name,duration,reminderTime,reminderDate,description)
    //     this.params.data.push([offeredServiceId, name, price, duration, description, reminderDate, reminderTime])
    //     // this.offeredServices.push(o)
    //     // Reset the name field for new people
    //     // this.newOfferedService = ''
    //   }
      
//     }
//   }