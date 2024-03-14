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

import java.util.List;
import java.util.Map;

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
        List<Category> categories = categoryRepository.findAll();
        return categories
                .stream()
                .map(CategoryMapper.INSTANCE::categoryToCategoryDTO)
                .toList();
    }

    public List<TagDTO> getTags() {
        List<Tag> tags = tagRepository.findAll();
        return tags
                .stream()
                .map(TagMapper.INSTANCE::tagToTagDTO)
                .toList();
    }

    public List<ProductDTO> getProductsByCategoryAndTagAndPriceRange(String categoryName, String tagName, double min, double max) {
        return productRepository.getProductsByCategoryAndTagAndPriceRange(categoryName, tagName, min, max)
                .stream()
                .map(ProductMapper.INSTANCE::productToProductDTO)
                .toList();
    }

    public boolean addProduct(ProductDTO productDTO) {
        Product product = ProductMapper.INSTANCE.productDTOToProduct(productDTO);
        return productRepository.save(product);
    }

    public boolean updateProduct(ProductDTO productDTO) {
        Product product = ProductMapper.INSTANCE.productDTOToProduct(productDTO);
        return productRepository.update(product);
    }
    public boolean deleteProduct(ProductDTO productDTO) {
        Product product = ProductMapper.INSTANCE.productDTOToProduct(productDTO);
        return productRepository.delete(product);
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper.INSTANCE::productToProductDTO)
                .toList();
    }
}
