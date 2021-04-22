package com.github.actar233.mbdpay.notice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Webhook 通知
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notice {

    /**
     * type
     */
    private NoticeType type;

    /**
     * data
     */
    private NoticeData data;

}
