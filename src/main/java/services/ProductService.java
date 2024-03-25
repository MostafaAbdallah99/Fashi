package services;

import mappers.ProductMapper;
import persistence.dto.ProductDTO;
import persistence.entities.Product;
import persistence.repository.repositories.ProductRepositoryImpl;
import persistence.repository.utils.TransactionUtil;

import java.util.List;
import java.util.Map;

public class ProductService {

    private final ProductRepositoryImpl productRepository;

    public ProductService() {
        this.productRepository = new ProductRepositoryImpl();
    }


    public Map<String, Object> getProductsByCategoryAndTagAndPriceRange(String categoryName, String tagName, double min, double max,int page, int size, String searchQuery) {
        return TransactionUtil.doInTransaction(entityManager -> {
            Map<String, Object> result = productRepository.getProductsByCategoryAndTagAndPriceRange(categoryName, tagName, min, max, page, size, searchQuery, entityManager);
            List<Product> products = (List<Product>) result.get("products");
            List<ProductDTO> productDTOs = products.stream().map(ProductMapper.INSTANCE::productToProductDTO).toList();
            result.put("products", productDTOs);
            return result;
        });
    }

    public boolean addProduct(ProductDTO productDTO) {
        Product product = ProductMapper.INSTANCE.productDTOToProduct(productDTO);
        return TransactionUtil.doInTransaction(entityManager -> productRepository.save(product, entityManager));
    }

    public boolean updateProduct(ProductDTO productDTO) {
        Product product = ProductMapper.INSTANCE.productDTOToProduct(productDTO);
        return TransactionUtil.doInTransaction(entityManager -> productRepository.update(product, entityManager));
    }

    public boolean deleteProduct(Long productId) {
        return TransactionUtil.doInTransaction(entityManager -> {
            Product product = productRepository.findReferenceById(productId, entityManager);
            return productRepository.delete(product, entityManager);
        });
    }

    public ProductDTO getProductById(Long productId) {
        return TransactionUtil.doInTransaction(entityManager -> {
            Product product = productRepository.findById(productId, entityManager);
            return ProductMapper.INSTANCE.productToProductDTO(product);
        });
    }

    public List<ProductDTO> getAllProducts() {
        return TransactionUtil.doInTransaction(productRepository::findAll)
                .stream()
                .map(ProductMapper.INSTANCE::productToProductDTO)
                .toList();
    }

    public List<ProductDTO> getProducts(int page, int size) {
        return TransactionUtil.doInTransaction(entityManager -> productRepository.getProducts(page, size, entityManager)
                .stream()
                .map(ProductMapper.INSTANCE::productToProductDTO)
                .toList());
    }

    public Map<String, Object> getTotalPages(int size) {
        return TransactionUtil.doInTransaction(entityManager -> productRepository.getTotalPages(size, entityManager));
    }
}
