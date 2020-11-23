package com.example.madlevel5task2.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel5task2.R
import com.example.madlevel5task2.model.Game
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_game_backlog.*

class GameBacklogFragment : Fragment() {

    private val gameBacklog = arrayListOf<Game>()
    private val gameBacklogAdapter = GameBacklogAdapter(gameBacklog)
    private val viewModel: GameBacklogViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_game_backlog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initViewModel()
    }

    private fun initViews() {
        rv_backlog.adapter = gameBacklogAdapter
        rv_backlog.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        createItemTouchHelper().attachToRecyclerView(rv_backlog)
    }

    private fun initViewModel() {
        viewModel.gameBacklog.observe(viewLifecycleOwner, Observer {
            gameBacklog.clear()
            gameBacklog.addAll(it)
            gameBacklogAdapter.notifyDataSetChanged()
        })

        viewModel.successDeleteGameBacklog.observe(viewLifecycleOwner, Observer { deletedGames ->
            val snackBar =
                Snackbar.make(rv_backlog, "Successfully deleted backlog", Snackbar.LENGTH_LONG)
            snackBar.setAction("UNDO") { viewModel.addGameBacklog(deletedGames) }
            snackBar.setActionTextColor(resources.getColor(android.R.color.holo_blue_dark))
            snackBar.show()
        })

        viewModel.successDeleteGame.observe(viewLifecycleOwner, Observer { deletedGame ->
            val snackBar =
                Snackbar.make(rv_backlog, "Successfully deleted game", Snackbar.LENGTH_LONG)
            snackBar.setAction("UNDO") { viewModel.addGame(deletedGame) }
            snackBar.setActionTextColor(resources.getColor(android.R.color.holo_blue_dark))
            snackBar.show()
        })
    }

    /**
     * Create a touch helper to recognize when a user swipes an item from a recycler view.
     * An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
     * and uses callbacks to signal when a user is performing these actions.
     */
    private fun createItemTouchHelper(): ItemTouchHelper {

        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val gameToDelete = gameBacklog[position]
                viewModel.deleteGame(gameToDelete)
            }
        }
        return ItemTouchHelper(callback)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete ->{
                viewModel.deleteGameBacklog()
                true}
            else -> super.onOptionsItemSelected(item)
        }
    }
}