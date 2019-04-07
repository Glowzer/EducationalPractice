class Protocol implements CMD, PORT {
    private static final byte CMD_MIN = CMD_CONNECT;
    private static final byte CMD_MAX = CMD_LETTER;

    static boolean validID(byte id) {
        return id >= CMD_MIN && id <= CMD_MAX;
    }
}
