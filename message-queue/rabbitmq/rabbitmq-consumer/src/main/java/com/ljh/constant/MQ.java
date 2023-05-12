package com.ljh.constant;

/**
 * MQ
 *
 * @author Arsenal
 * @since 2023/5/12 16:27
 */
public class MQ {

    public static class Exchange {

        public static class Order {
            public static final String name = "order-exchange";
            public static final String type = "topic";
        }

        public static class Order2 {
            public static final String name = "order-exchange2";
            public static final String type = "topic";
            public static final String ignoreDeclarationExceptions = "true";
        }

        public static class Order3 {
            public static final String name = "order-exchange3";
            public static final String type = "topic";
        }
    }

    public static class Queue {

        public static class Order {
            public static final String name = "order-queue";
            public static final String durable = "true";
        }

        public static class Order2 {
            public static final String name = "order-queue2";
            public static final String durable = "true";
        }

        public static class Order3 {
            public static final String name = "order-queue3";
            public static final String durable = "true";
        }
    }

    public static class Bindings {
        public static final String KEY = "order.*";
    }

    public static class Routing {
        public static final String KEY = "order.001";
        public static final String KEY2 = "order.002";
        public static final String KEY3 = "order.003";
    }

    public static class Status {
        public static final int SENDING = 0;
        public static final int SEND_SUCCESS = 1;
        public static final int SEND_FAILURE = 2;
    }
}
