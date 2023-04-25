package com.lcsz.chatgpt.autoconfigure;

/**
 * Custom exception class for chatgpt-related errors
 *
 * @author zhangdingfei
 * @date 2023/4/25 22:20
 */
public class ChatgptException extends RuntimeException {

    /**
     * Constructs a new ChatException with the specified detail message.
     *
     * @param msg the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public ChatgptException(String msg) {
        super(msg);
    }

    /**
     * Constructs a new ChatException with the specified detail message.
     *
     * @param msg   the detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause the cause
     */
    public ChatgptException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
