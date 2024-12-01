package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;

import com.example.demo.service.PartService;
import com.example.demo.service.PartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;
    ProductService productService;
    PartService partService;
    Product prodTemp;
    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
        this.productService = new ProductServiceImpl(productRepository);
        this.partService = new PartServiceImpl(partRepository);
    }

    @Override
    public void run(String... args) throws Exception {

        /* Example Parts
        OutsourcedPart o = new OutsourcedPart();
        o.setCompanyName("Western Governors University");
        o.setName("out test");
        o.setInv(5);
        o.setPrice(20.0);
        o.setId(100L);
        outsourcedPartRepository.save(o);
        OutsourcedPart thePart = null;
        List<OutsourcedPart> outsourcedParts = (List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            if(part.getName().equals("out test"))thePart = part;
        }

        System.out.println(thePart.getCompanyName());
        */

        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            System.out.println(part.getName()+" "+part.getCompanyName());
        }

        /* Example Product
        Product bicycle = new Product("bicycle",100.0,15);
        Product unicycle = new Product("unicycle",100.0,15);
        productRepository.save(bicycle);
        productRepository.save(unicycle);
        */

        // Check if part and product repositories are empty
        if (partRepository.count() < 10) {

            // Sample parts
            partRepository.deleteAll();
            productRepository.deleteAll();
            
            Part part1 = new InhousePart(101, "Engine", 1500.00, 10);
            Part part2 = new InhousePart(102, "Transmission", 2000.00, 5);
            Part part3 = new OutsourcedPart("BrakeSupplier", "Brakes", 500.00, 20);
            Part part4 = new InhousePart(103, "Suspension", 800.00, 8);
            Part part5 = new OutsourcedPart("TireSupplier", "Tires", 300.00, 30);
            // Add parts to repository
            partService.save(part1);
            partService.save(part2);
            partService.save(part3);
            partService.save(part4);
            partService.save(part5);
       
           
            // Sample products
            // Product product1 = new Product("Sedan", 20000.00, 2, part1, part3);
            // Product product2 = new Product("SUV", 30000.00, 3, part2, part5);
            // Product product3 = new Product("Truck", 40000.00, 1, part1, part4);
            // Product product4 = new Product("Coupe", 25000.00, 4, part3, part5);
            // Product product5 = new Product("Convertible", 35000.00, 2, part2, part4);
            Product product1 = new Product("Sedan", 20000.00, 2);
   
            Product product2 = new Product("SUV", 30000.00, 3);
            Product product3 = new Product("Truck", 40000.00, 1);
            Product product4 = new Product("Coupe", 25000.00, 4);
            Product product5 = new Product("Convertible", 35000.00, 2);
            
            // Add products to repository
            productService.save(product1);
            productService.save(product2);
            productService.save(product3);
            productService.save(product4);
            productService.save(product5);

            
            // Added by Tutor:
            prodTemp=product1;
            addPart((int)part1.getId(),(int)part2.getId());
            
            prodTemp=product2;
            addPart((int)part2.getId());

            prodTemp=product3;
            addPart((int)part3.getId(),(int)part4.getId(),(int)part5.getId());

            System.out.println("Sample inventory added: 5 parts and 5 products.");
        } else {
            System.out.println("Parts and/or products already exist, no sample data added.");
        }

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products: " + productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts: " + partRepository.count());
        System.out.println(partRepository.findAll());
    }
    
    // Method Added by Tutor:
	private void addPart (int... parts)
	{
		try {
			ProductService productService = new ProductServiceImpl(productRepository);

			for (int partId:parts)
			{
	            Part part = partService.findById(partId);
	            prodTemp.getParts().add(part);
				part.getProducts().add(prodTemp);
			}
			productService.save(prodTemp);

			//partService.save(part);
			for (int partId:parts)
			{
				partService.save(partService.findById(partId));
			}
			//System.out.printf ("theID: partId: %d, productId: $d\n", partId, prodTemp.getId());
		}catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
