

class AsyncNet {
    //region private domain
    _state = common.defaultUserState()
    _update(tip = '') {
        this._state.tip = tip
        this._watchers.forEach((watcher) => {
            watcher(this._state)
        })
    }
    _watchers = []
    //endregion
    //region const domain
    net_fail = "net_fail"
    uri_user_login = "/api/user/login"
    uri_user_state = "/api/user/state"
    uri_user_logout = "/api/user/logout"
    uri_org_all = "/api/org/all"
    uri_org_get = "/api/org/get"
    uri_org_list = "/api/org/list"
    uri_org_grouped = "/api/org/grouped"
    uri_org_user_invite = "/api/org/user_invite"
    uri_org_get_person = "/api/org/person/get"
    uri_org_search_person = "/api/org/person/search"
    uri_org_invite_get = "/api/org/invite/get"
    uri_org_invite_get_person = "/api/org/invite/get/person"
    uri_org_invite_org2person = "/api/org/invite/org2person"
    uri_org_invite_process = "/api/org/invite/process"
    uri_course_all = "/api/course/all"
    uri_org_create = "/api/org/create"
    uri_file_upload = "/api/file/upload"
    uri_course_get = "/api/course/get"
    uri_course_create = "/api/course/create"
    uri_course_update = "/api/course/update"
    uri_course_list_admin = "/api/course/list/admin"
    uri_course_chapter_get = "/api/course/chapter/get"
    uri_course_chapter_create = "/api/course/chapter/create"
    uri_course_chapter_update = "/api/course/chapter/update"
    uri_course_chapter_move = "/api/course/chapter/move"
    uri_course_chapter_delete = "/api/course/chapter/delete"
    uri_course_resource_get = "/api/course/resource/get"
    uri_course_resource_create = "/api/course/resource/create"
    uri_course_media_get = "/api/course/media/get"
    uri_course_selectid="/api/course/selectid"
    file_img = "img"
    //endregion
    //region public domain
    // add restAPI support
    /**
     * 添加监听器，用于监听_state的状态更改
     * @param watcher
     */
    addWatcher(watcher) {
        this._watchers.push(watcher)
    }
    loaded() {
        this._update('')
    }

    /**
     * 通过网络请求获取当前用户的状态
     * @returns {Promise<State>}
     */
    async userState() {
        let res = await this.post(this.uri_user_state, '', false);
        if (res === this.net_fail) {
            this._state.tip = '获取用户信息失败'
        } else if(res.code !== 200)  {
            this._state.tip = res.message
        } else {
            this._state.tip = ''
            this._state.user = res.data
        }
        return this._state
    }
    /**
     * 进行一个post请求
     * @param url 资源路径
     * @param body 请求体
     * @param doUpdate
     * @returns {Promise<string|response<any>>}
     */
    async post(url, body = '', doUpdate = true) {
        let formRequest = new Request(url, {
            method: 'post',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
            },
            body: body
        })
        let res = await fetch(formRequest);
        if (res.status !== 200){
            if (doUpdate) this._update(`${this.net_fail} status:${res.status}`)
            return this.net_fail
        } else {
            // result must match @response format
            let result = await res.json()
            if (result.code === 200){
                if (doUpdate) this._update()
            } else {
                if (doUpdate) this._update(result.message)
            }
            return result
        }
    }

    /**
     * 通过api/user/login 进行登录
     * @param uid
     * @param password
     * @returns {Promise<ResponseUser>}
     */
    async userLogin(uid, password) {
        return this.post(this.uri_user_login, `uid=${uid}&password=${password}`)
    }

    /**
     * 用户退出登录
     * @return {Promise<Response<any>>}
     */
    async userLogout() {
        return this.post(this.uri_user_logout)
    }

    /**
     * 通过api/course/selectid 进行课程查询
     * @param id
     * @returns {Promise<string|response<*>>}
     */
    async courseSelect(id){
        return this.post(this.uri_course_selectid,`id=${id}`)
    }



    /**
     * 通过api/org/all 获取所有organization的信息
     * @returns {Promise<ResponseOrganizations>}
     */
    async orgAll() {
        return this.post(this.uri_org_all)
    }

    /**
     * 通过api/course/all 获取所有course的信息
     * @returns {Promise<ResponseCourses>}
     */
    async courseAll() {
        return this.post(this.uri_course_all)
    }

    /**
     * 通过api/course/list/admin 获取所有我管理的课程
     * @return {Promise<ResponseCourses>}
     */
    async courseListAdmin() {
        return this.post(this.uri_course_list_admin)
    }

    /**
     * @deprecated
     * 通过api/org/grouped 获取分组过的organization的信息
     * @return {Promise<ResponseOrganizationGrouped>}
     */
    async orgGrouped() {
        return this.post(this.uri_org_grouped)
    }

    /**
     * @deprecated
     * 通过api/org/user_invite进行加入部门的申请
     * @param orgId
     * @return {Promise<ResponseOrganization>}
     */
    async orgUserInvite(orgId) {
        return this.post(this.uri_org_user_invite, `orgId=${orgId}`)
    }



    /**
     * 通过api/org/list获取部门信息
     * @return {Promise<ResponseOrganizations>}
     */
    async orgList() {
        return this.post(this.uri_org_list)
    }

    /**
     * 通过api/org/get获取部门的细节信息
     * @param orgId
     * @return {Promise<ResponseOrganization>}
     */
    async orgGet(orgId) {
        return this.post(this.uri_org_get, `orgId=${orgId}`)
    }

    /**
     * 通过api/org/person/get 获取人员列表
     * @param orgId
     * @return {Promise<ResponseUsers>}
     */
    async orgGetPerson(orgId) {
        return this.post(this.uri_org_get_person, `orgId=${orgId}`)
    }

    /**
     * 通过api/org/person/search 搜索用户
     * @param orgId
     * @param query
     * @return {Promise<ResponseUsers>}
     */
    async orgSearchPerson(orgId, query) {
        return this.post(this.uri_org_search_person, `orgId=${orgId}&query=${query}`)
    }

    /**
     * 通过api/org/invite/get 搜索申请列表
     * @param orgId
     * @return {Promise<ResponseUserOrgNodeInvitations>}
     */
    async orgInviteList(orgId) {
        return this.post(this.uri_org_invite_get, `orgId=${orgId}`)
    }

    /**
     * 通过api/org/invite/get/person 搜索申请列表
     * @return {Promise<ResponseUserOrgNodeInvitations>}
     */
    async orgInviteListPerson() {
        return this.post(this.uri_org_invite_get_person)
    }

    /**
     * 通过api/org/invite/org2person 进行邀请
     * @param orgId
     * @param personUid
     * @return {Promise<Response<any>>}
     */
    async orgInvitePerson(orgId, personUid) {
        return this.post(this.uri_org_invite_org2person, `orgId=${orgId}&personUid=${personUid}`)
    }

    /**
     * 通过api/org/create 创建组织
     * @param name {string}
     * @param description {string}
     * @param isPublic {boolean}
     * @return {Promise<ResponseOrganization>}
     */
    async orgCreate(name, description, isPublic) {
        return this.post(this.uri_org_create, `name=${name}&description=${description}&public=${isPublic}`)
    }



    /**
     *
     * @param inviteId {number}
     * @param accept {boolean}
     * @return {Promise<Response<any>>}
     */
    async orgProcessInvite(inviteId, accept) {
        return this.post(this.uri_org_invite_process, `inviteId=${inviteId}&accept=${accept}`)
    }

    /**
     * 上传文件
     * @param formData {FormData}
     * @param type {string}
     * @return {Promise<string | Response>}
     */
    async fileUpload(formData, type) {
        let formRequest = new Request(`${this.uri_file_upload}/${type}`, {
            method: 'post',
            credentials: 'include',
            body: formData
        })
        let res = await fetch(formRequest)
        if (res.status === 200){
            return await res.json()
        } else {
            return this.net_fail
        }
    }

    /**
     * 通过接口/api/course/get获取课程的基础信息
     * @param courseId
     * @return {Promise<ResponseCourse>}
     */
    async courseGet(courseId) {
        return this.post(this.uri_course_get, `courseId=${courseId}`)
    }



    /**
     * 通过接口/api/course/create创建课程
     * @param name {string}
     * @param info {string}
     * @param pic {string}
     * @param isPublic {boolean}
     * @return {Promise<ResponseCourse>}
     */
    async courseCreate(name, info, pic, isPublic) {
        return this.post(this.uri_course_create, `name=${name}&info=${info}&pic=${pic}&public=${isPublic}`)
    }

    /**
     * 通过api/course/update更新信息
     * @param courseId
     * @param name
     * @param info
     * @param pic
     * @param isPublic
     * @return {Promise<ResponseCourse>}
     */
    async courseUpdate(courseId, name, info, pic, isPublic) {
        return this.post(this.uri_course_update, `id=${courseId}&name=${name}&info=${info}&pic=${pic}&public=${isPublic}`)
    }

    /**
     * 通过接口/api/course/chapter/get获取章节
     * @param courseId
     * @return {Promise<ResponseChapters>}
     */
    async courseGetChapters(courseId) {
        return this.post(this.uri_course_chapter_get, `courseId=${courseId}`)
    }

    /**
     * 通过接口/api/course/chapter/create创建章节
     * @param courseId
     * @param name
     * @param index
     * @return {Promise<ResponseChapter>}
     */
    async courseCreateChapter(courseId, name, index) {
        return this.post(this.uri_course_chapter_create, `courseId=${courseId}&name=${name}&index=${index}`)
    }

    /**
     * 通过接口/api/course/chapter/update更新章节
     * @param courseId
     * @param chapterId
     * @param name
     * @return {Promise<ResponseChapter>}
     */
    async courseUpdateChapter(courseId, chapterId, name) {
        return this.post(this.uri_course_chapter_update, `courseId=${courseId}&chapterId=${chapterId}&name=${name}`)
    }

    /**
     * 通过接口/api/course/chapter/move移动章节
     * @param courseId
     * @param chapterId
     * @param index
     * @return {Promise<ResponseChapter>}
     */
    async courseMoveChapter(courseId, chapterId, index) {
        return this.post(this.uri_course_chapter_move, `courseId=${courseId}&chapterId=${chapterId}&index=${index}`)
    }

    /**
     * 通过接口/api/course/chapter/delete删除章节
     * @param courseId
     * @param chapterId
     * @return {Promise<ResponseChapter>}
     */
    async courseDeleteChapter(courseId, chapterId) {
        return this.post(this.uri_course_chapter_delete, `courseId=${courseId}&chapterId=${chapterId}`)
    }

    /**
     * 通过接口/api/course/resource/get获取资源列表
     * @param courseId
     * @return {Promise<ResponseResources>}
     */
    async courseGetResources(courseId) {
        return this.post(this.uri_course_resource_get, `courseId=${courseId}`)
    }

    /**
     * 通过接口/api/course/resource/create创建资源
     * @param courseId
     * @param name
     * @param type
     * @param data
     * @return {Promise<ResponseResource>}
     */
    async courseCreateResource(courseId, name, type, data) {
        return this.post(this.uri_course_resource_create, `courseId=${courseId}&name=${name}&type=${type}&data=${data}`)
    }

    /**
     * 通过接口/api/course/media/get获取内容
     * @param chapterId
     * @return {Promise<ResponseMedias>}
     */
    async courseGetMedias(chapterId) {
        return this.post(this.uri_course_media_get, `chapterId=${chapterId}`)
    }

    //endregion
}

let net = new AsyncNet()