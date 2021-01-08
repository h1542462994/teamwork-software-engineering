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