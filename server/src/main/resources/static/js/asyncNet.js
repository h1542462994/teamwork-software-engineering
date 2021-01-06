

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
    uri_org_all = "/api/org/all"
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
     * @returns {Promise<state>}
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
     * @returns {Promise<response_user>}
     */
    async userLogin(uid, password) {
        return this.post(this.uri_user_login, `uid=${uid}&password=${password}`)
    }

    /**
     * 通过api/org/all 获取所有organization的信息
     * @returns {Promise<response_organizations>}
     */
    async orgAll() {
        return this.post(this.uri_org_all)
    }

    //endregion
}

let net = new AsyncNet()