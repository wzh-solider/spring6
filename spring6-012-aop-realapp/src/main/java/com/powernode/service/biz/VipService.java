package com.powernode.service.biz;

import org.springframework.stereotype.Service;

/**
 * @author Solider
 * @version 1.0
 * @Date 2023/9/5 17:15
 * @since 1.0
 */
@Service
public class VipService {

        public void saveVip() {
            System.out.println("新增会员信息");
        }

        public void updateVip() {
            System.out.println("修改会员信息");
        }

        public void deleteVip() {
            System.out.println("删除会员信息");
        }

        public void getVip() {
            System.out.println("查询会员信息");
        }
}
