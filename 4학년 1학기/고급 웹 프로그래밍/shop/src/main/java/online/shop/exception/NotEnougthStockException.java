package online.shop.exception;

public class NotEnougthStockException extends RuntimeException {
    private static final String message = "재고가 부족합니다";

    public NotEnougthStockException() {
    }

    public NotEnougthStockException(String message) {
        super(message);
    }

    public NotEnougthStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnougthStockException(Throwable cause) {
        super(cause);
    }

    public NotEnougthStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NotEnougthStockException(int stockQuantity, int quantity) {
        super(message + " 재고 : " + stockQuantity + " 주문 : " + quantity);
    }
}
