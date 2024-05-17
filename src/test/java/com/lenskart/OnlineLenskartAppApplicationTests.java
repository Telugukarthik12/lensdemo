package com.lenskart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.lenskart.dto.CategoryDTO;
import com.lenskart.dto.UserDTO;
import com.lenskart.entity.AdminEntity;
import com.lenskart.entity.Category;
import com.lenskart.entity.CustomerEntity;
import com.lenskart.repository.AdminRepository;
import com.lenskart.repository.CategoryRepository;
import com.lenskart.repository.CustomerRepository;
import com.lenskart.serviceimpl.AdminServiceImpl;
import com.lenskart.serviceimpl.CategoryServiceImpl;
import com.lenskart.serviceimpl.CustomerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class OnlineLenskartAppApplicationTests {

	@InjectMocks
	CustomerServiceImpl customerServiceImpl;
	@InjectMocks
	CategoryServiceImpl categoryServiceImpl;
	@InjectMocks
	AdminServiceImpl adminServiceImpl;

	@Mock
	CustomerRepository customerRepository;

	@Mock
	CategoryRepository categoryRepository;
	@Mock
	AdminRepository adminRepository;

	@Test
	public void getCustomersTest()
	{
		when(customerRepository.findAll()).thenReturn(Stream.of(new CustomerEntity(1,"abc","123","pqr@email",123,"AP"),new CustomerEntity(2,"abc","123","pqqqr@email",123,"AP")).collect(Collectors.toList()));
		assertEquals(2, customerServiceImpl.readAllCustomers().size());
	}

	@Test
	public void addCustomerTest() {
		CustomerEntity customer = new CustomerEntity(3, "pqw", "123", "wwr@email", 123, "AP");
		UserDTO userDTO = new UserDTO(3, "pqw", "123", "wwr@email", 123, "AP");
		when(customerRepository.save(customer)).thenReturn(customer);
		UserDTO reuserDTO = customerServiceImpl.registerCustomer(userDTO);
		assertEquals(reuserDTO.getUsername(), customer.getUsername());
		assertEquals(reuserDTO.getEmail(), customer.getEmail());
		assertEquals(reuserDTO.getId(), customer.getId());

	}

	@Test
	public void deleteCustomerTest() {

		doNothing().when(customerRepository).deleteById(99);

		customerServiceImpl.deleteCustomer(99);

		verify(customerRepository, times(1)).deleteById(99);

	}

	@Test
	public void addCategoryTest() {
		Category category = new Category(3, "pqw");
		CategoryDTO categoryDTO = new CategoryDTO(3, "pqw");
		when(categoryRepository.save(category)).thenReturn(category);
		CategoryDTO recategoryDTO = categoryServiceImpl.addCategory(categoryDTO);
		assertEquals(recategoryDTO.getCategoryId(), category.getCategoryId());
		assertEquals(recategoryDTO.getCategoryName(), category.getCategoryName());

	}

	@Test
	public void deleteCategoryTest() {

		doNothing().when(categoryRepository).deleteById(99);

		categoryServiceImpl.removeCategory(99);

		verify(categoryRepository, times(1)).deleteById(99);

	}

	@Test
	public void findByCategoryIdTest() {
		Category category = new Category(3, "pqw");
		when(categoryRepository.findById(3)).thenReturn(Optional.of(category));
		CategoryDTO recategoryDTO = categoryServiceImpl.searchCategoryById(3);
		assertEquals(recategoryDTO.getCategoryId(), category.getCategoryId());
		assertEquals(recategoryDTO.getCategoryName(), category.getCategoryName());

	}

	@Test
	public void findByCategoryNameTest() {
		Category category = new Category(3, "pqw");
		when(categoryRepository.findByCategoryName("pqw")).thenReturn(category);
		CategoryDTO recategoryDTO = categoryServiceImpl.seachCategoryByName("pqw");
		assertEquals(recategoryDTO.getCategoryId(), category.getCategoryId());
		assertEquals(recategoryDTO.getCategoryName(), category.getCategoryName());

	}

	@Test
	public void addAdminTest() {
		AdminEntity admin = new AdminEntity(3, "pqw", "123", "wwr@email", 123, "AP");
		UserDTO userDTO = new UserDTO(3, "pqw", "123", "wwr@email", 123, "AP");
		when(adminRepository.save(admin)).thenReturn(admin);
		UserDTO reuserDTO = adminServiceImpl.addAdmin(userDTO);
		assertEquals(reuserDTO.getUsername(), admin.getUsername());
		assertEquals(reuserDTO.getEmail(), admin.getEmail());
		assertEquals(reuserDTO.getId(), admin.getId());

	}

	@Test
	public void findAdminByEmailTest() {
		AdminEntity admin = new AdminEntity(3, "pqw", "123", "wwr@email", 123, "AP");
		when(adminRepository.findByEmail("wwr@email")).thenReturn(admin);
		UserDTO reuserDTO = adminServiceImpl.getAdminByEmail("wwr@email");
		assertEquals(reuserDTO.getUsername(), admin.getUsername());
		assertEquals(reuserDTO.getEmail(), admin.getEmail());
		assertEquals(reuserDTO.getId(), admin.getId());

	}

}
