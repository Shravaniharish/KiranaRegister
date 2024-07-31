package api.kirana.register.stores.controller;

import api.kirana.register.response.ApiResponse;
import api.kirana.register.stores.controllers.StoresController;
import api.kirana.register.stores.entity.Stores;
import api.kirana.register.stores.models.StoresDTO;
import api.kirana.register.stores.service.StoresService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StoresControllerTest {
    @Mock
    private StoresService storeService;

    @InjectMocks
    private StoresController storeController;

    @Test
    public void getAllStoresShouldReturnSuccess() {
        int page = 2;
        int size = 4;
        List<Stores> stores = Arrays.asList(
                new Stores(), new Stores(), new Stores(), new Stores()
        );
        Page<Stores> storeList = new PageImpl<>(stores);
        Pageable pageable = PageRequest.of(page, size);
        ApiResponse response = new ApiResponse();
        response.setData(storeList);
        Mockito.when(storeService.getAllStore(pageable)).thenReturn(storeList);
        ResponseEntity<ApiResponse> responseEntity = storeController.getAllStores(page,size);
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(storeList, responseEntity.getBody().getData());
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }
    @Test
    public void getStoreByIdShouldReturnSuccess() {
        Stores store = new Stores();
        store.setId("test_id");
        Optional<Stores> expectedStore = Optional.of(store);

        Mockito.when(storeService.getStoreById("test_id")).thenReturn(expectedStore);

        ResponseEntity<ApiResponse> responseEntity = storeController.getStoreById("test_id");
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(expectedStore, responseEntity.getBody().getData());
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void saveStoreShouldReturnSuccess() {
        Stores store = new Stores();
        store.setId("test_id");

        StoresDTO storeDTO = new StoresDTO();
        storeDTO.setName("Test Store");
        storeDTO.setAddress("123 Test Street");
        storeDTO.setPhoneNumber("9876543210");

        Mockito.when(storeService.saveStore(Mockito.any(StoresDTO.class))).thenReturn(store);

        ResponseEntity<ApiResponse> responseEntity = storeController.saveStore(storeDTO);
        Assertions.assertNotNull(responseEntity);
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(store, responseEntity.getBody().getData());
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


    @Test
    public void deleteStoreShouldReturnSuccess() {
        String storeId = "test_id";
        Mockito.when(storeService.deleteStore(storeId)).thenReturn("Store deleted successfully");

        ResponseEntity<ApiResponse> responseEntity = storeController.deleteStore(storeId);
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals("Store deleted successfully", responseEntity.getBody().getData());
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}