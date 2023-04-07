package com.trantuyen.springecommerce;

import com.github.javafaker.Faker;
import com.trantuyen.springecommerce.entity.Category;
import com.trantuyen.springecommerce.entity.Customer;
import com.trantuyen.springecommerce.entity.Product;
import com.trantuyen.springecommerce.repo.CartRepo;
import com.trantuyen.springecommerce.repo.CategoryRepo;
import com.trantuyen.springecommerce.repo.CustomerRepo;
import com.trantuyen.springecommerce.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@SpringBootApplication
public class SpringEcommerceApplication implements CommandLineRunner {
    @Autowired
    private Faker faker;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private CartRepo cartRepo;

    public static void main(String[] args) {
        SpringApplication.run(SpringEcommerceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        generateFakeCategory(10);
//        generateFakeProducts(10);
//        generateFakeCustomers(10);
        generateData();
    }

    private void generateFakeCategory(int count) {
        List<Category> categories = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Category category = new Category();
            category.setName(faker.name().bloodGroup());
            categories.add(category);
        }
        categoryRepo.saveAll(categories);
    }

    private void generateData() {
        Optional<Customer> customer = customerRepo.findByUsername("user");
        if(customer.isEmpty()){
            Customer customer1 = new Customer();
            customer1.setName("User Mock");
            customer1.setUsername("user");
            customer1.setPassword(passwordEncoder.encode("123456"));
            customerRepo.save(customer1);
        }
    }

    private void generateFakeProducts(int count) {
        Random rand = new Random();
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Product product = new Product();
            product.setName(faker.commerce().productName());
            product.setBrand(faker.company().name());
            product.setPrice(BigDecimal.valueOf(faker.number().randomDouble(2, 1, 100)));
            product.setColor(faker.commerce().color());
            Long categoryId = Long.valueOf(rand.nextInt(10 - 1 + 1) + 1);
            Category category = new Category();
            category.setId(categoryId);
            product.setCategoryByCategoryId(category);
            products.add(product);
        }
        productRepo.saveAll(products);
    }

    private void generateFakeCustomers(int count) {
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Customer customer = new Customer();
            customer.setName(faker.name().fullName());
            customer.setUsername(faker.internet().emailAddress());
            customer.setPassword(passwordEncoder.encode("123456"));
            customers.add(customer);
        }
        customerRepo.saveAll(customers);
    }

}
