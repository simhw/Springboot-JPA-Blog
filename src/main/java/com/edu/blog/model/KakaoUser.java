package com.edu.blog.model;

import lombok.Data;

@Data
public class KakaoUser {
    public Integer id;
    public String connected_at;
    public Properties properties;

    @Data
    public class Properties {
        public String nickname;
    }

    public Kakao_account kakao_account;

    @Data
    public class Kakao_account {
        public Boolean profile_nickname_needs_agreement;
        public Boolean profile_image_needs_agreement;
        public Profile profile;

        @Data
        public class Profile {
            public String nickname;
            public String thumbnail_image_url;
            public String profile_image_url;
            public Boolean is_default_image;
        }

        public Boolean has_email;
        public Boolean email_needs_agreement;
        public Boolean is_email_valid;
        public Boolean is_email_verified;
        public String email;
    }
}

