package api.kirana.register.store.controllers;
import api.kirana.register.response.Response;
import api.kirana.register.store.models.StoreDTO;
import api.kirana.register.store.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/store")
public class StoreController {
    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    /**
     * Retrieves a list of all stores.
     *
     * @return a ResponseEntity containing the response with all stores
     */
    @GetMapping("/getAllStores")
    public ResponseEntity<Response>  getAllStores() {
        Response response = new Response();
        response.setData(storeService.getAllStore());
        response.setMessage("List of all stores");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Retrieves a store by its ID.
     *
     * @param id the ID of the store to retrieve
     * @return a ResponseEntity containing the response with the store information
     */
    @GetMapping("/getStoreById")
    public ResponseEntity<Response> getStoreById(@RequestParam String id) {
        Response response = new Response();
        response.setData(storeService.getStoreById(id));
        response.setMessage("store id match");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Saves a new store.
     *
     * @param storeRequest the request body containing store information to save
     * @return a ResponseEntity containing the response with the saved store information
     */
    @PostMapping("/saveStore")
    public ResponseEntity<Response> saveStore(@RequestBody StoreDTO storeRequest) {
        Response response = new Response();
        response.setData(storeService.saveStore(storeRequest));
        response.setMessage("store details are saved");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Deletes a store by its ID.
     *
     * @param id the ID of the store to delete
     * @return a ResponseEntity containing the response indicating the deletion status
     */
    @DeleteMapping("/deleteMapping")
    public ResponseEntity<Response> deleteStore(@RequestParam String id) {
        Response response = new Response();
        response.setData(storeService.deleteStore(id));
        response.setMessage("store deleted!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
