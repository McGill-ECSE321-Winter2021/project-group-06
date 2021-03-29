export default {
    data() {
      return {
        tableData: [{
          Id:"SERVICE1",
          name: "SERVICE1",
          duration: 10,
          price: 20.1,
          comments: "quick and easy",
          reminderDate: 10,
          reminderTime: "09:00:00"
        }],
        dialogTableVisible: false,
        dialogFormVisible: false,
        form: {
          Id: '',
          name: '',
          duration: 0,
          price: 0.0,
          comments: '',
          reminderDate: 0,
          reminderTime: '',
        //   desc: ''
        },
        formLabelWidth: '120px',
        packData:[],
        delVisible: false,//删除提示弹框的状态
        msg:"",//记录每一条的信息，便于取id
        msg2:"",
        delarr:[],//存放删除的数据
        multipleSelection: [],//多选的数据
        modifyData:[],
        prevValue:{}
      }
    },

    mounted(){
        this.getData()
    },

    methods: {
        handleEdit(index, row) {
            console.log(index, row);
            this.msg = row;
            this.msg2 = index;
        },

        Edit(Id,name,duration,price,description,reminderDate,reminderTime){
            var newValue2 = {
                Id: Id,
                name: name,
                duration: duration,
                price: price,
                comments: description,
                reminderDate: reminderDate,
                reminderTime: reminderTime
            }
            this.tableData.push(newValue2)
            this.tableData.splice(this.mesg2, 1)
            this.delVisible = false;
        },

        getPackData() {
                this.$axios.post('/api/selectPackPageMade.do').then((res) => {
                    this.packData = res.data;
                }).catch(function(error){
                    console.log(error);
                })
            },

        handleDelete(index, row) {
                    this.idx = index;
                    this.msg= row;//每一条数据的记录
                    this.delarr.push(this.msg.Id);//把单行删除的每条数据的id添加进放删除数据的数组
                    this.delVisible = true;
        },

        deleteRow(){
            this.tableData.splice(this.idx, 1)
            this.delVisible = false;
        },

        createOfferedService: function (offeredServiceId, price,name,duration,reminderTime,reminderDate,description) {
            var newValue = {
                Id: offeredServiceId,
                name: name,
                duration: duration,
                price: price,
                comments: description,
                reminderDate: reminderDate,
                reminderTime: reminderTime
            }
            this.tableData.push(newValue)
        },
    },


    
  }