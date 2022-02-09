package com.su.defect.config_defect;

import com.su.analysis.Http_req_pars;
import com.su.analysis.Jwt_pars;
import com.su.defect.Defect;

public abstract class F_config_defect  extends Defect {

    public Jwt_pars getJwt_pars() {
        return jwt_pars;
    }

    public void setJwt_pars(Jwt_pars jwt_pars) {
        this.jwt_pars = jwt_pars;
    }

    private Jwt_pars jwt_pars;

    @Override
    public abstract Boolean scan();
}
