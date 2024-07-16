package api.kirana.register.store.controller;

import api.kirana.register.response.Response;
import api.kirana.register.store.controllers.StoreController;
import api.kirana.register.store.models.Store;
import api.kirana.register.store.models.StoreDTO;
import api.kirana.register.store.service.StoreService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StoreControllerTest {
    @Mock
    private StoreService storeService;

    @InjectMocks
    private StoreController storeController;

    @Test
    public void getAllStoresShouldReturnSuccess() {
        Store store = new Store();
        List<Store> storeList = new ArrayList<>();
        storeList.add(store);
        Response response = new Response();
        response.setData(storeList);
        response.setMessage("List of all store");
        Mockito.when(storeService.getAllStore()).thenReturn(storeList);
        ResponseEntity<Response> responseEntity = storeController.getAllStores();
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(storeList, responseEntity.getBody().getData());
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }
    @Test
    public void getStoreByIdShouldReturnSuccess() {
        Store store = new Store();
        store.setStoreId("test_id");
        Optional<Store> expectedStore = Optional.of(store);

        Mockito.when(storeService.getStoreById("test_id")).thenReturn(expectedStore);

        ResponseEntity<Response> responseEntity = storeController.getStoreById("test_id");
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(expectedStore, responseEntity.getBody().getData());
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals("store id match", responseEntity.getBody().getMessage());
    }

    @Test
    public void saveStoreShouldReturnSuccess() {
        Store store = new Store();
        store.setStoreId("test_id");

        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setStoreName("Test Store");
        storeDTO.setStoreAddress("123 Test Street");
        storeDTO.setStoreContactNo("9876543210");

        Mockito.when(storeService.saveStore(Mockito.any(StoreDTO.class))).thenReturn(store);

        ResponseEntity<Response> responseEntity = storeController.saveStore(storeDTO);
        Assertions.assertNotNull(responseEntity);
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(store, responseEntity.getBody().getData());
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals("store details are saved", responseEntity.getBody().getMessage());
    }


    @Test
    public void deleteStoreShouldReturnSuccess() {
        String storeId = "test_id";
        Mockito.when(storeService.deleteStore(storeId)).thenReturn("Store deleted successfully");

        ResponseEntity<Response> responseEntity = storeController.deleteStore(storeId);
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals("Store deleted successfully", responseEntity.getBody().getData());
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals("store deleted!", responseEntity.getBody().getMessage());
    }
}