package dto;



import lombok.Data;

@Data
public class CartDTO {

    private Long userId;
    private Long productId;
    private int quantity;
	public Long getUserId() {
		// TODO Auto-generated method stub
		return null;
	}
	public Long getProductId() {
		// TODO Auto-generated method stub
		return null;
	}
	public int getQuantity() {
		// TODO Auto-generated method stub
		return 0;
	}
}