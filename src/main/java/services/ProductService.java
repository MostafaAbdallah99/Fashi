package services;

import mappers.ProductMapper;
import persistence.dto.ProductDTO;
import persistence.entities.Product;
import persistence.repository.repositories.ProductRepositoryImpl;
import persistence.repository.utils.TransactionUtil;

import java.util.List;

public class ProductService {

    private final ProductRepositoryImpl productRepository;

    public ProductService() {
        this.productRepository = new ProductRepositoryImpl();
    }


    public List<ProductDTO> getProductsByCategoryAndTagAndPriceRange(String categoryName, String tagName, double min, double max) {
        return TransactionUtil.doInTransaction(entityManager -> productRepository.getProductsByCategoryAndTagAndPriceRange(categoryName, tagName, min, max, entityManager)
                .stream()
                .map(ProductMapper.INSTANCE::productToProductDTO)
                .toList());
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
}
