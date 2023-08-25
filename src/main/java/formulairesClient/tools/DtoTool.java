package formulairesClient.tools;



import org.modelmapper.ModelMapper;

public class DtoTool {
    private static ModelMapper mapper = new ModelMapper();

//    public static Product convertToProduct(ProductDto dto){
//        return mapper.map(dto, Product.class);
//    }
//    public static ProductDto convertToProduct(Product p){
//        return mapper.map(p, ProductDto.class);
//    }

    /**
     * Methode qui convertie une entity en dto et vice versa
     * @param <TSource> Type Source
     * @param <TDestination> Type Destination
     * @param obj Objet à convertir
     * @param clazz Type de l'objet converti
     * @return l'objet converti
     */
    public static <TSource, TDestination> TDestination convert(TSource obj, Class<TDestination> clazz){
        
        return mapper.map(obj, clazz);
    }
}
