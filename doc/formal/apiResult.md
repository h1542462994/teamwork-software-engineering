# apiResult

## UserController

### login（登录）

```
/api/user/login

uid=test&password=123456
```

```json
{
  "code": 200,
  "summary": "ok",
  "message": "ok",
  "data": {
    "uid": "test",
    "name": "测试人员",
    "age": 18,
    "sex": false,
    "email": "test@outlook.com"
  }
}
```

### register（注册）

```
/api/user/register

uid=cht&name=陈昊天&age=20&password=123456&rePassword=123456&sex=false&email=t1542462994@outlook.com
```

```json
{
  "code": 200,
  "summary": "ok",
  "message": "ok",
  "data": {
    "uid": "cht",
    "name": "陈昊天",
    "age": 20,
    "sex": false,
    "email": "t1542462994@outlook.com"
  }
}
```

```json
{
  "code": 403,
  "summary": "failed",
  "message": "该用户已经注册",
  "data": null
}
```

### state

```
/api/user/state
```

```json
{
  "code": 401,
  "summary": "noLogin",
  "message": "未登录无法访问该资源",
  "data": "当前未登录"
}
```

```json
{
  "code": 200,
  "summary": "ok",
  "message": "ok",
  "data": {
    "uid": "test",
    "name": "测试人员",
    "age": 18,
    "sex": false,
    "email": "test@outlook.com"
  }
}
```

## OrgController

### create

#### 1 加入一个组织

```
/api/org/create

name=计算机学院&description=计算机学院的组织&public=false
```

```json
{
  "code": 200,
  "summary": "ok",
  "message": "ok",
  "data": {
    "id": 3,
    "name": "计算机学院",
    "description": "计算机学院的组织",
    "parentId": null,
    "public": false
  }
}
```

#### 2 加入一个部门节点

```
/api/org/create

name=软件工程01&description=软件工程的班级&parentId=1
```

```json
{
  "code": 200,
  "summary": "ok",
  "message": "ok",
  "data": {
    "id": 5,
    "name": "软件工程01",
    "description": "软件工程的班级",
    "parentId": 1,
    "public": false
  }
}
```

```json
{
  "code": 403,
  "summary": "failed",
  "message": "你没有权限进行该操作",
  "data": null
}
```

#### list

```
/api/org/list
```

```json
{
  "code": 200,
  "summary": "ok",
  "message": "ok",
  "data": [
    {
      "id": 1,
      "name": "计算机学院",
      "description": "计算机学院的组织",
      "children": [],
      "public": false,
      "owner": {
        "uid": "",
        "name": "",
        "age": 1,
        "sex": false,
        "email": ""
      },
      "level": 2
    },
    {
      "id": 2,
      "name": "计算机学院",
      "description": "计算机学院的组织",
      "children": [],
      "public": false,
      "owner": {
        "uid": "",
        "name": "",
        "age": 1,
        "sex": false,
        "email": ""
      },
      "level": 2
    },
    {
      "id": 3,
      "name": "计算机学院",
      "description": "计算机学院的组织",
      "children": [],
      "public": false,
      "owner": {
        "uid": "",
        "name": "",
        "age": 1,
        "sex": false,
        "email": ""
      },
      "level": 2
    }
  ]
}
```

### all（仅供测试）

```
/api/org/all
```

```json
{
  "code": 200,
  "summary": "ok",
  "message": "ok",
  "data": [
    {
      "id": 1,
      "name": "计算机学院",
      "description": "计算机学院的组织",
      "children": [
        {
          "id": 4,
          "name": "软件工程01",
          "description": "软件工程的班级",
          "children": []
        },
        {
          "id": 5,
          "name": "软件工程01",
          "description": "软件工程的班级",
          "children": []
        }
      ],
      "public": false,
      "owner": {
        "uid": "test",
        "name": "测试人员",
        "age": 18,
        "sex": false,
        "email": "test@outlook.com"
      }
    },
    {
      "id": 2,
      "name": "计算机学院",
      "description": "计算机学院的组织",
      "children": [],
      "public": false,
      "owner": {
        "uid": "test",
        "name": "测试人员",
        "age": 18,
        "sex": false,
        "email": "test@outlook.com"
      }
    },
    {
      "id": 3,
      "name": "计算机学院",
      "description": "计算机学院的组织",
      "children": [],
      "public": false,
      "owner": {
        "uid": "test",
        "name": "测试人员",
        "age": 18,
        "sex": false,
        "email": "test@outlook.com"
      }
    }
  ]
}
```

## MessageController

### post

```
/api/message/post

type=ORGANIZATION&message=这是一则测试的消息&url=/org/1
```

```json
{
  "code": 200,
  "summary": "ok",
  "message": "ok",
  "data": {
    "id": 8,
    "type": "ORGANIZATION",
    "message": "这是一则测试的消息",
    "url": "/org/1",
    "createTime": "2021-01-08 23:05:36.69",
    "read": false
  }
}
```

### get

```
/api/message/get?[timeStamp=?]
```

```json
{
  "code": 200,
  "summary": "ok",
  "message": "ok",
  "data": [
    {
      "id": 1,
      "type": "ORGANIZATION",
      "message": "这是一则测试的消息",
      "url": "/org/1",
      "createTime": "2021-01-08 14:50:48.640",
      "read": false
    },
    {
      "id": 2,
      "type": "ORGANIZATION",
      "message": "这是一则测试的消息",
      "url": "/org/1",
      "createTime": "2021-01-08 14:53:53.286",
      "read": false
    },
    {
      "id": 3,
      "type": "ORGANIZATION",
      "message": "这是一则测试的消息",
      "url": "/org/1",
      "createTime": "2021-01-08 14:54:59.736",
      "read": false
    },
    {
      "id": 4,
      "type": "ORGANIZATION",
      "message": "这是一则测试的消息",
      "url": "/org/1",
      "createTime": "2021-01-08 15:00:28.528",
      "read": false
    }
  ]
}
```

### read

```
/api/message/read?messageId=4
```

```json
{
  "code": 200,
  "summary": "ok",
  "message": "ok",
  "data": {
    "id": 4,
    "type": "ORGANIZATION",
    "message": "这是一则测试的消息",
    "url": "/org/1",
    "createTime": "2021-01-08 15:00:28.528",
    "read": true
  }
}
```