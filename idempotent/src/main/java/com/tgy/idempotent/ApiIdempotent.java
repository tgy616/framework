package com.tgy.idempotent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解@ApiIdempotent
 *
 * 在需要保证 接口幂等性 的Controller的方法上使用此注解
 * @author DragonSwimDiving
 * @program idempotent
 * @Date 2020-05-14 18:10
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiIdempotent {
}
