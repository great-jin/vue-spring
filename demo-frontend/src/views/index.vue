<template>
  <div id="app">
    <div>
      <a-input v-model="code" placeholder="Code"  style="margin-bottom: 10px"/>
      <a-input v-model="user.accountCode" placeholder="Account Code"/>
      <a-input v-model="user.userName" placeholder="User Name"/>
      <a-input v-model="user.password" placeholder="Password"/>
      <a-input v-model="backResult" placeholder="Result" disabled="disabled"/>
    </div>

    <div>
      <a-button type="primary" @click="list" class="bt">List</a-button>
      <a-button type="primary" @click="getUser" class="bt">User</a-button>
      <a-button type="primary" @click="login" class="bt"> Login</a-button>
      <a-button type="primary" @click="clear" class="bt">Clear</a-button>
    </div>

  </div>
</template>

<script>
import { List, getUser, Login } from '@/api/user.js';
export default {
  name: 'Index',
  data() {
    return {
      code: '',
      backResult:'',
      user: {
        accountCode: '',
        userName: '',
        password: ''
      }
    }
  },
  methods: {
    list() {
      List().then(res =>{
        console.log(res)
        this.backResult = res
      })
    },
    getUser() {
      const params = this.code
      console.log(params)
      if (params !== '') {
        getUser(params).then(res =>{
          console.log(res)
          this.backResult = res
          this.$message.success(res)
        })
      } else {
        this.$message.error('Pleace input the code!')
      }
    },
    login() {
      const params = this.user
      console.log(params)
      if (params.accountCode !== '' && params.userName !== '' && params.password !== '') {
        Login(params).then(res =>{
          console.log(res)
          if (res === 1){
            this.backResult = 'OK'
          } else {
            this.backResult = 'Faild'
          }
        })
      } else {
        this.$message.error('Pleace input the user info!')
      }
    },
    clear() {
      this.code = ''
      this.user.accountCode = ''
      this.user.userName = ''
      this.user.password = ''
      this.backResult = ''
    }
  },
}
</script>

<style>
  #app {
    width: 500px;
    margin: 20px auto;
    text-align: center;
    color: #2c3e50;
  }
  .bt{
    margin-top:20px
  }
</style>
