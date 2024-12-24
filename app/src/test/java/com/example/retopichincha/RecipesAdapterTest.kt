import android.widget.ImageView
import com.example.retopichincha.databinding.ItemRecipeBinding
import com.example.retopichincha.domain.model.RecipesModel
import com.example.retopichincha.presentation.adapter.RecipeAdapter
import org.junit.Test
import org.mockito.kotlin.verify
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RecipeAdapterTest {

    @Mock
    private lateinit var onFavoriteClick: (RecipesModel) -> Unit

    @Mock
    private lateinit var onItemClick: (RecipesModel) -> Unit

    @Mock
    private lateinit var mockBinding: ItemRecipeBinding

    @Mock
    private lateinit var recipeAdapter: RecipeAdapter

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        recipeAdapter = RecipeAdapter(onFavoriteClick, onItemClick)
    }

    @Test
    fun testOnItemClick() {
        val recipe = RecipesModel(
            id = 1,
            name = "Spaghetti Carbonara",
            image = "https://upload.wikimedia.org/wikipedia/commons/3/33/Espaguetis_carbonara.jpg",
            description = "Un clásico italiano con queso y panceta.",
            ingredients = listOf(
                "200g Spaghetti",
                "100g Panceta",
                "2 Huevos",
                "50g Queso parmesano",
                "Sal y pimienta"
            ),
            steps = listOf(
                "Cocina los spaghetti.",
                "Fríe la panceta.",
                "Mezcla con huevos y queso.",
                "Sirve caliente."
            )
        )

        val viewHolder = recipeAdapter.RecipeViewHolder(mockBinding)
        viewHolder.bind(recipe)

        viewHolder.itemView.performClick()
        verify(onItemClick).invoke(recipe)
    }

}