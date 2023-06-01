import LoadingButton from "./LoadingButton";
import SelectPlate from "./SelectPlate";
import SelectDanger from "./SelectDanger";

const SelectBox = (probs) => {

    return (
        <>
            <form className="row gy-2 gx-3 align-items-center">
                <div className="col-auto">
                    <input className="form-select-sm" type="date" ref={probs.reqDate} onChange={probs.handleDate} name="reqdate" style={{ border: "1px lightgray solid" }} />
                </div>
                <div className="col-auto">
                    <SelectPlate plate={probs.plate} />
                </div>
                <div className="col-auto">
                    <SelectDanger danger={probs.danger} />
                </div>
                <div className="col-auto">
                    <LoadingButton searchData={probs.searchData} next={probs.next} />
                </div>
            </form>
        </>
    )
}

export default SelectBox;