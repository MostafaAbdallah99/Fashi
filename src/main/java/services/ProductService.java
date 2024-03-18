package services;

import mappers.CategoryMapper;
import mappers.ProductMapper;
import mappers.TagMapper;
import persistence.dto.CategoryDTO;
import persistence.dto.ProductDTO;
import persistence.dto.TagDTO;
import persistence.entities.Category;
import persistence.entities.Product;
import persistence.entities.Tag;
import persistence.repository.repositories.CategoryRepositoryImpl;
import persistence.repository.repositories.ProductRepositoryImpl;
import persistence.repository.repositories.TagRepositoryImpl;
import persistence.repository.utils.TransactionUtil;

import java.util.List;

public class ProductService {

    private final CategoryRepositoryImpl categoryRepository;
    private final TagRepositoryImpl tagRepository;
    private final ProductRepositoryImpl productRepository;

    public ProductService() {
        this.categoryRepository = new CategoryRepositoryImpl(Category.class);
        this.tagRepository = new TagRepositoryImpl(Tag.class);
        this.productRepository = new ProductRepositoryImpl(Product.class);
    }

    public List<CategoryDTO> getCategories() {
        List<Category> categories = TransactionUtil.doInTransaction(categoryRepository::findAll);
        return categories
                .stream()
                .map(CategoryMapper.INSTANCE::categoryToCategoryDTO)
                .toList();
    }

    public List<TagDTO> getTags() {
        List<Tag> tags = TransactionUtil.doInTransaction(tagRepository::findAll);
        return tags
                .stream()
                .map(TagMapper.INSTANCE::tagToTagDTO)
                .toList();
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
