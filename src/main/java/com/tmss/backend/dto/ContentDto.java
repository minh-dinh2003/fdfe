package com.tmss.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class ContentDto {
    private List<Op> ops;

    @Data
    public static class Op {
        private String insert;
        private Attributes attributes;

        @Data
        public static class Attributes {
            private String codeBlock; // Thuộc tính có thể có
        }
    }
}
