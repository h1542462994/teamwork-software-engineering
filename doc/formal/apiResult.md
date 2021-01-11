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

### list

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
      "children": [
        {
          "id": 4,
          "name": "策划部",
          "description": "软件工程的班级",
          "children": []
        },
        {
          "id": 5,
          "name": "人力资源部",
          "description": "软件工程的班级",
          "children": []
        }
      ],
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
      "name": "公关部",
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

### get

```
/api/org/get

orgId=1
```

```json
{
  "code": 200,
  "summary": "ok",
  "message": "ok",
  "data": {
    "id": 1,
    "name": "计算机学院",
    "description": "计算机学院的组织",
    "children": [
      {
        "id": 4,
        "name": "",
        "description": "软件工程的班级",
        "children": [
          {
            "id": 12,
            "name": "财务部1",
            "description": "该财务部负责公司日常财务核算，参与公司的经营管理。",
            "children": [
              {
                "id": 13,
                "name": "财务部2",
                "description": "根据公司资金运作情况，合理调配资金，确保公司资金正常运转。",
                "children": [],
                "level": 2
              },
              {
                "id": 14,
                "name": "财务部3",
                "description": "搜集公司经营活动情况、资金动态、营业收入和费用开支的资料并进行分析、提出建议，定期向总经理报告。",
                "children": [],
                "level": 2
              }
            ],
            "level": 2
          }
        ],
        "level": 2
      },
      {
        "id": 5,
        "name": "",
        "description": "软件工程的班级",
        "children": [],
        "level": 2
      }
    ],
    "level": 2,
    "public": false,
    "owner": {
      "uid": "test",
      "name": "测试人员",
      "age": 18,
      "sex": false,
      "email": "test@outlook.com"
    }
  }
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

## CourseController

### all（仅供测试）

```
/api/course/all
```

```json
{
  "code": 200,
  "summary": "ok",
  "message": "ok",
  "data": [
    {
      "id": 1,
      "pic": null,
      "name": "网络工程原理",
      "info": "网络工程原理是浙江工业大学计算机学院学生大二学习的课程",
      "inEdit": false,
      "createTime": "2021-01-08 15:00:28.528",
      "editTime": "2021-01-08 15:00:28.528",
      "courseTags": [
        {
          "id": 1,
          "name": "编译语言"
        },
        {
          "id": 2,
          "name": "网络管理"
        },
        {
          "id": 3,
          "name": "前端程序"
        }
      ],
      "owner": null,
      "adminUsers": []
    }
  ]
}
```

### create

```
/api/course/create

name=计算机组成原理&info=计算机组成原理必修课程，其前修课程有&pic=uisafdsafsdfsd&public=false
```

```json
{
  "code": 200,
  "summary": "ok",
  "message": "ok",
  "data": {
    "id": 61,
    "pic": "uisafdsafsdfsd",
    "name": "计算机组成原理",
    "info": "计算机组成原理必修课程，其前修课程有",
    "inEdit": true,
    "createTime": "2021-01-09 16:32:22.80",
    "editTime": "2021-01-09 16:32:22.80",
    "courseTags": [],
    "owner": {
      "uid": "test",
      "name": "测试人员",
      "age": 18,
      "sex": false,
      "email": "test@outlook.com"
    },
    "adminUsers": [],
    "public": false
  }
}
```

### update

```
/api/course/update

id=61&name=软件工程&info=软件工程专业必修课程&pic=uisafdadfafadf&public=false
```

```json
{
  "code": 200,
  "summary": "ok",
  "message": "ok",
  "data": {
    "id": 61,
    "pic": "uisafdadfafadf",
    "name": "软件工程",
    "info": "软件工程专业必修课程",
    "inEdit": true,
    "createTime": "2021-01-09 16:32:22.80",
    "editTime": "2021-01-09 20:27:50.938",
    "courseTags": [],
    "owner": {
      "uid": "test",
      "name": "测试人员",
      "age": 18,
      "sex": false,
      "email": "test@outlook.com"
    },
    "adminUsers": [],
    "public": false
  }
}
```

### delete

```json
POST http://{{host}}/api/course/delete
Content-Type: application/x-www-form-urlencoded

courseId=60
```

```json
{
  "code": 200,
  "summary": "ok",
  "message": "ok",
  "data": true
}
```

### createTag

```
POST http://{{host}}/api/course/tag/create
Content-Type: application/x-www-form-urlencoded

courseId=61&name=编译语言
```

```json
{
  "code": 200,
  "summary": "ok",
  "message": "ok",
  "data": [
    {
      "id": 9,
      "name": "程序员"
    },
    {
      "id": 10,
      "name": "计算机"
    },
    {
      "id": 1,
      "name": "编译语言"
    }
  ]
}
```

### removeTag

```
POST http://{{host}}/api/course/tag/delete
Content-Type: application/x-www-form-urlencoded

courseId=61&tagId=1
```

```json
{
  "code": 200,
  "summary": "ok",
  "message": "ok",
  "data": [
    {
      "id": 9,
      "name": "程序员"
    },
    {
      "id": 10,
      "name": "计算机"
    }
  ]
}
```

### getChapters

```
POST http://{{host}}/api/course/chapter/get
Content-Type: application/x-www-form-urlencoded

courseId=61
```

```json
{
  "code": 200,
  "summary": "ok",
  "message": "ok",
  "data": [
    {
      "id": 2,
      "name": "第一章"
    }
  ]
}
```

### createChapter

```
POST http://{{host}}/api/course/chapter/create
Content-Type: application/x-www-form-urlencoded

courseId=61&name=第一章&index=0
```

```json
{
  "code": 200,
  "summary": "ok",
  "message": "ok",
  "data": [
    {
      "id": 2,
      "name": "第一章"
    },
    {
      "id": 1,
      "name": "第一章"
    }
  ]
}
```

### updateChapter

```
POST http://{{host}}/api/course/chapter/update
Content-Type: application/x-www-form-urlencoded

courseId=61&chapterId=1&name=第二章
```

```json
{
  "code": 200,
  "summary": "ok",
  "message": "ok",
  "data": [
    {
      "id": 2,
      "name": "第一章"
    },
    {
      "id": 1,
      "name": "第二章"
    }
  ]
}
```

### moveChapter

```
POST http://{{host}}/api/course/chapter/move
Content-Type: application/x-www-form-urlencoded

courseId=61&chapterId=1&index=0
```

```json
{
  "code": 200,
  "summary": "ok",
  "message": "ok",
  "data": [
    {
      "id": 1,
      "name": "第二章"
    },
    {
      "id": 2,
      "name": "第一章"
    }
  ]
}
```

### deleteChapter

```
POST http://{{host}}/api/course/chapter/delete
Content-Type: application/x-www-form-urlencoded

courseId=61&chapterId=1
```

```json
{
  "code": 200,
  "summary": "ok",
  "message": "ok",
  "data": [
    {
      "id": 2,
      "name": "第一章"
    }
  ]
}
```