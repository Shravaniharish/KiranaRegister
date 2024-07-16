package api.kirana.register.store.service;

import api.kirana.register.store.models.Store;
import api.kirana.register.store.models.StoreDTO;
import api.kirana.register.store.repo.StoreDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StoreServiceTest {
    @Mock
    private StoreDAO storeDAO;

    @InjectMocks
    private StoreServiceImpl storeService;

    @Test
    public void getAllStoreShouldReturnSuccess(){
        Store store = new Store();
        List<Store> storeList = new ArrayList<>();
        storeList.add(store);
        Mockito.when(storeDAO.getAllStore()).thenReturn(storeList);
        List<Store> actualList = storeService.getAllStore();
        Assertions.assertNotNull(actualList);
        Assertions.assertEquals(storeList, actualList);
    }

    @Test
    public void getStoreByIdShouldReturnSuccess() {
        Store store = new Store();
        store.setStoreId("test");
        Optional<Store> expectedStore = Optional.of(store);
        Mockito.when(storeDAO.getStoreById(store.getStoreId())).thenReturn(expectedStore);
        Optional<Store> actualStore = storeService.getStoreById(store.getStoreId());
        Assertions.assertNotNull(actualStore);
        Assertions.assertEquals(expectedStore, actualStore);
    }

    @Test
    public void saveStoreShouldReturnSuccess() {
        Store store = new Store();
        store.setStoreName("abc");
        store.setStoreContactNo("0987654321");
        store.setStoreAddress("xyz");

        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setStoreName("abc");
        storeDTO.setStoreContactNo("0987654321");
        storeDTO.setStoreAddress("xyz");
        Mockito.when(storeDAO.saveStore(Mockito.any(Store.class))).thenReturn(store);
        Store actualStore = storeService.saveStore(storeDTO);
        Assertions.assertNotNull(actualStore);
        Assertions.assertEquals(store.getStoreAddress(), actualStore.getStoreAddress());
        Assertions.assertEquals(store.getStoreName(), actualStore.getStoreName());
        Assertions.assertEquals(store.getStoreContactNo(), actualStore.getStoreContactNo());


    }
    @Test
    public void deleteStoreShouldReturnSuccess(){
        String storeId = "test";
        String expectedResponse = "Store is deleted";
        Mockito.doNothing().when(storeDAO).deleteStore(storeId);
        String actualResponse = storeService.deleteStore(storeId);
        Mockito.verify(storeDAO, Mockito.times(1)).deleteStore(storeId);
        Assertions.assertEquals(expectedResponse, actualResponse);

    }

}
