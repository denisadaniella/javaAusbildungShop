package ro.msg.learning.shop.odata.edm;

import org.apache.olingo.odata2.api.edm.EdmMultiplicity;
import org.apache.olingo.odata2.api.edm.EdmSimpleTypeKind;
import org.apache.olingo.odata2.api.edm.FullQualifiedName;
import org.apache.olingo.odata2.api.edm.provider.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ODEdmProvider extends EdmProvider {

    public static final String ENTITY_NAME_ORDERCREATION = "Order";
    public static final String ENTITY_SET_NAME_ORDERCREATIONS = "Orders";
    public static final String ENTITY_NAME_PRODUCTORDER = "Product";
    public static final String ENTITY_SET_NAME_PRODUCTORDERS = "Products";

    private static final String NAMESPACE = "org.apache.olingo.odata2.ODataShop";
    private static final String ENTITY_CONTAINER = "ODataShopEntityContainer";

    private static final FullQualifiedName ENTITY_TYPE_OC = new FullQualifiedName(NAMESPACE, ENTITY_NAME_ORDERCREATION);
    private static final FullQualifiedName ENTITY_TYPE_PO = new FullQualifiedName(NAMESPACE, ENTITY_NAME_PRODUCTORDER);
    private static final FullQualifiedName COMPLEX_TYPE_ADDRESS = new FullQualifiedName(NAMESPACE, "Address");

    private static final FullQualifiedName ASSOCIATION_OC_PO = new FullQualifiedName(NAMESPACE, "Order_Products_Product_Order");
    private static final String ROLE_OC_PO = "Order_Products";
    private static final String ROLE_PO_OC = "Product_Order";

    private static final String ASSOCIATION_SET = "Orders_Products";


    @Override
    public List<Schema> getSchemas() {
        List<Schema> schemas = new ArrayList<>();

        Schema schema = new Schema();
        schema.setNamespace(NAMESPACE);

        List<EntityType> entityTypes = new ArrayList<>();
        entityTypes.add(getEntityType(ENTITY_TYPE_OC));
        entityTypes.add(getEntityType(ENTITY_TYPE_PO));
        schema.setEntityTypes(entityTypes);

        List<ComplexType> complexTypes = new ArrayList<>();
        complexTypes.add(getComplexType(COMPLEX_TYPE_ADDRESS));
        schema.setComplexTypes(complexTypes);

        List<Association> associations = new ArrayList<>();
        associations.add(getAssociation(ASSOCIATION_OC_PO));
        schema.setAssociations(associations);

        List<EntityContainer> entityContainers = new ArrayList<>();
        EntityContainer entityContainer = new EntityContainer();
        entityContainer.setName(ENTITY_CONTAINER).setDefaultEntityContainer(true);

        List<EntitySet> entitySets = new ArrayList<>();
        entitySets.add(getEntitySet(ENTITY_CONTAINER, ENTITY_SET_NAME_ORDERCREATIONS));
        entitySets.add(getEntitySet(ENTITY_CONTAINER, ENTITY_SET_NAME_PRODUCTORDERS));
        entityContainer.setEntitySets(entitySets);

        List<AssociationSet> associationSets = new ArrayList<>();
        associationSets.add(getAssociationSet(ENTITY_CONTAINER, ASSOCIATION_OC_PO, ENTITY_SET_NAME_ORDERCREATIONS, ROLE_OC_PO));
        entityContainer.setAssociationSets(associationSets);

        entityContainers.add(entityContainer);
        schema.setEntityContainers(entityContainers);

        schemas.add(schema);
        return schemas;
    }

    @Override
    public EntityType getEntityType(FullQualifiedName edmFQName) {
        if (NAMESPACE.equals(edmFQName.getNamespace())) {

            if (ENTITY_TYPE_OC.getName().equals(edmFQName.getName())) {

                List<Property> properties = new ArrayList<>();
                properties.add(new SimpleProperty().setName("orderId").setType(EdmSimpleTypeKind.Int32).setFacets(new Facets().setNullable(false)));
                properties.add(new SimpleProperty().setName("created").setType(EdmSimpleTypeKind.DateTime).setFacets(new Facets().setNullable(true)));
                properties.add(new ComplexProperty().setName("deliveryAddress").setType(new FullQualifiedName(NAMESPACE, "Address")));

                List<NavigationProperty> navigationProperties = new ArrayList<>();
                navigationProperties.add(new NavigationProperty().setName("products")
                        .setRelationship(ASSOCIATION_OC_PO).setFromRole(ROLE_OC_PO).setToRole(ROLE_PO_OC));

                List<PropertyRef> keyProperties = new ArrayList<>();
                keyProperties.add(new PropertyRef().setName("orderId"));
                Key key = new Key().setKeys(keyProperties);

                return new EntityType().setName(ENTITY_TYPE_OC.getName())
                        .setProperties(properties)
                        .setKey(key)
                        .setNavigationProperties(navigationProperties);

            } else if (ENTITY_TYPE_PO.getName().equals(edmFQName.getName())) {

                List<Property> properties = new ArrayList<>();
                properties.add(new SimpleProperty().setName("productId").setType(EdmSimpleTypeKind.Int32).setFacets(new Facets().setNullable(false)));
                properties.add(new SimpleProperty().setName("quantity").setType(EdmSimpleTypeKind.Int32).setFacets(new Facets().setNullable(false)));

                List<NavigationProperty> navigationProperties = new ArrayList<>();
                navigationProperties.add(new NavigationProperty().setName("order")
                        .setRelationship(ASSOCIATION_OC_PO).setFromRole(ROLE_PO_OC).setToRole(ROLE_OC_PO));

                List<PropertyRef> keyProperties = new ArrayList<>();
                keyProperties.add(new PropertyRef().setName("productId"));
                Key key = new Key().setKeys(keyProperties);

                return new EntityType().setName(ENTITY_TYPE_PO.getName())
                        .setProperties(properties)
                        .setKey(key)
                        .setNavigationProperties(navigationProperties);
            }
        }

        return null;
    }


    @Override
    public ComplexType getComplexType(FullQualifiedName edmFQName) {
        if (NAMESPACE.equals(edmFQName.getNamespace()) && COMPLEX_TYPE_ADDRESS.getName().equals(edmFQName.getName())) {
            List<Property> properties = new ArrayList<>();
            properties.add(new SimpleProperty().setName("country").setType(EdmSimpleTypeKind.String));
            properties.add(new SimpleProperty().setName("county").setType(EdmSimpleTypeKind.String));
            properties.add(new SimpleProperty().setName("city").setType(EdmSimpleTypeKind.String));
            properties.add(new SimpleProperty().setName("street").setType(EdmSimpleTypeKind.String));
            return new ComplexType().setName(COMPLEX_TYPE_ADDRESS.getName()).setProperties(properties);
        }


        return null;
    }

    @Override
    public Association getAssociation(FullQualifiedName edmFQName) {
        if (NAMESPACE.equals(edmFQName.getNamespace()) && ASSOCIATION_OC_PO.getName().equals(edmFQName.getName())) {
            return new Association().setName(ASSOCIATION_OC_PO.getName())
                    .setEnd1(new AssociationEnd().setType(ENTITY_TYPE_OC).setRole(ROLE_OC_PO).setMultiplicity(EdmMultiplicity.ONE))
                    .setEnd2(new AssociationEnd().setType(ENTITY_TYPE_PO).setRole(ROLE_PO_OC).setMultiplicity(EdmMultiplicity.MANY));
        }

        return null;
    }

    @Override
    public EntitySet getEntitySet(String entityContainer, String name) {
        if (ENTITY_CONTAINER.equals(entityContainer)) {
            if (ENTITY_SET_NAME_ORDERCREATIONS.equals(name)) {
                return new EntitySet().setName(name).setEntityType(ENTITY_TYPE_OC);
            } else if (ENTITY_SET_NAME_PRODUCTORDERS.equals(name)) {
                return new EntitySet().setName(name).setEntityType(ENTITY_TYPE_PO);
            }
        }
        return null;
    }


    @Override
    public AssociationSet getAssociationSet(String entityContainer, FullQualifiedName association, String sourceEntitySetName, String sourceEntitySetRole) {
        if (ENTITY_CONTAINER.equals(entityContainer) && ASSOCIATION_OC_PO.equals(association)) {
            return new AssociationSet().setName(ASSOCIATION_SET)
                    .setAssociation(ASSOCIATION_OC_PO)
                    .setEnd1(new AssociationSetEnd().setRole(ROLE_OC_PO).setEntitySet(ENTITY_SET_NAME_ORDERCREATIONS))
                    .setEnd2(new AssociationSetEnd().setRole(ROLE_PO_OC).setEntitySet(ENTITY_SET_NAME_PRODUCTORDERS));
        }

        return null;
    }

    @Override
    public EntityContainerInfo getEntityContainerInfo(String name) {
        if (name == null || ENTITY_CONTAINER.equals(name)) {
            return new EntityContainerInfo().setName(ENTITY_CONTAINER).setDefaultEntityContainer(true);
        }
        return null;
    }
}
