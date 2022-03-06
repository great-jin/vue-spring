export const tableColumns = [
  {
    title: '编号',
    dataIndex: 'id',
    width: '10%',
    align: 'center',
    scopedSlots: { customRender: 'id' },
  },
  {
    title: '账号',
    dataIndex: 'accountCode',
    width: '15%',
    align: 'center',
    scopedSlots: { customRender: 'accountCode' },
  },
  {
    title: '用户名',
    dataIndex: 'userName',
    width: '20%',
    ellipsis: true,
    align: 'center',
    scopedSlots: { customRender: 'userName' }
  },
  {
    title: '密码',
    dataIndex: 'password',
    width: '20%',
    align: 'center',
    scopedSlots: { customRender: 'password' },
  },
  {
    title: '状态',
    dataIndex: 'isDelete',
    width: '15%',
    align: 'center',
    scopedSlots: { customRender: 'status' }
  },
  {
    title: '操作',
    width: '25%',
    align: 'center',
    dataIndex: 'operation',
    scopedSlots: { customRender: 'operation' },
  }
]
