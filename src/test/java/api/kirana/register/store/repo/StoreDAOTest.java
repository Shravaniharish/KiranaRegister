package api.kirana.register.store.repo;

import api.kirana.register.store.models.Store;
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
public class StoreDAOTest {
    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private StoreDAO storeDAO;

    @Test
    public void getAllStoreShouldReturnSuccess() {
        Store store = new Store();
        List<Store> storeList = new ArrayList<>();
        storeList.add(store);
        Mockito.when(storeRepository.findAll()).thenReturn(storeList);
        List<Store> actualList = storeDAO.getAllStore();
        Assertions.assertNotNull(actualList);
        Assertions.assertEquals(storeList, actualList);
    }

    @Test
    public void getStoreByIdShouldReturnSuccess() {
        Store store = new Store();
        store.setStoreId("test");
        Optional<Store> expectedStore = Optional.of(store);
        Mockito.when(storeRepository.findById(store.getStoreId())).thenReturn(expectedStore);
        Optional<Store> actualStore = storeDAO.getStoreById(store.getStoreId());
        Assertions.assertNotNull(actualStore);
        Assertions.assertEquals(expectedStore, actualStore);
    }

    @Test
    public void saveStoreShouldReturnSuccess() {
        Store store = new Store();
        store.setStoreId("test");
        store.setStoreName("xyz");
        store.setStoreAddress("Koramangala");
        store.setStoreContactNo("0987654321");
        Mockito.when(storeRepository.save(store)).thenReturn(store);
        Store actualStore = storeDAO.saveStore(store);
        Assertions.assertNotNull(actualStore);
        Assertions.assertEquals(store, actualStore);

    }

    @Test
    public void deleteStoreShouldReturnSuccess() {
        String storeId = "test";
        Mockito.doNothing().when(storeRepository).deleteById(storeId);
        storeDAO.deleteStore(storeId);
        Mockito.verify(storeRepository,Mockito.times(1)).deleteById(storeId);


    }

}
