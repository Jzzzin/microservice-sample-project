package com.bloknoma.apigateway.members;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;

// Member Service URL 외부화 구성
@ConfigurationProperties(prefix = "member.destinations")
public class MemberDestinations {

    @NotNull
    private String memberServiceUrl;

    public String getMemberServiceUrl() {
        return memberServiceUrl;
    }

    public void setMemberServiceUrl(String memberServiceUrl) {
        this.memberServiceUrl = memberServiceUrl;
    }
}
