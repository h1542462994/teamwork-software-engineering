

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
    /**
     * 进行一个post请求
     * @param url 资源路径
     * @param body 请求体
     * @param doUpdate
     * @returns {Promise<string|any>}
     */
    async post(url, body, doUpdate = true) {
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
            if (doUpdate) this._update(this.net_fail)
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
    //endregion
}

let net = new AsyncNet()